package com.company.project.auth.service.impl;

import com.company.project.auth.dao.TagMapper;
import com.company.project.auth.model.Blog;
import com.company.project.auth.model.Tag;
import com.company.project.auth.service.TagService;
import com.company.project.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
@Service
@Transactional
public class TagServiceImpl extends AbstractService<Tag> implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Transactional
    @Override
    public void saveTag(String tagName) {

        tagMapper.saveTag(tagName);
    }

    @Override
    public List<Tag> getTags() {
        List<Tag> tags = tagMapper.getTags();
        return tags;
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        Tag t = tagMapper.getTag(id);
        return t;
    }

//    @Transactional
//    @Override
//    public Page<Type> listType(Pageable pageable) {
//        return null;
//    }

    @Transactional
    @Override
    public void updateTag(Long id, Tag tag) {
        tagMapper.updateTag(id,tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public Long selectBlogId(Long tag_id) {
        Long blogId = tagMapper.selectBlogId(tag_id);
        return blogId;
    }

    @Override
    public List<Blog> blog_tagNum(Long tag_id) {
        List<Blog> blogs = tagMapper.blog_tagNum(tag_id);
        if(blogs!=null){
            return blogs;
        }
        return null;
    }

}
