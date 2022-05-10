package com.company.project.auth.service.impl;

import com.company.project.auth.dao.TypeMapper;
import com.company.project.auth.model.Blog;
import com.company.project.auth.model.Type;
import com.company.project.auth.service.TypeService;
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
public class TypeServiceImpl extends AbstractService<Type> implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public void saveType(String typeName) {

        typeMapper.saveType(typeName);
    }

    @Override
    public List<Type> getTypes() {
        List<Type> types = typeMapper.getTypes();
        return types;
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        Type t = typeMapper.getType(id);
        return t;
    }

//    @Transactional
//    @Override
//    public Page<Type> listType(Pageable pageable) {
//        return null;
//    }

    @Transactional
    @Override
    public void updateType(Long id, Type type) {
        typeMapper.updateType(id,type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }

    @Override
    public Long selectBlogId(Long type_id) {
        Long blogId = typeMapper.selectBlogId(type_id);
        return blogId;
    }

    @Override
    public List<Blog> blog_typeNum(Long type_id) {
        List<Blog> blogs = typeMapper.blog_typeNum(type_id);
        if (blogs!=null){
            return blogs;
        }
        return null;
    }
}
