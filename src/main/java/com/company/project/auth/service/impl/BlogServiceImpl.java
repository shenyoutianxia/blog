package com.company.project.auth.service.impl;

import com.company.project.auth.dao.BlogMapper;
import com.company.project.auth.model.*;
import com.company.project.auth.service.BlogService;
import com.company.project.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
@Service
@Transactional
public class BlogServiceImpl extends AbstractService<Blog> implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    //根据blog的id查找blog_type表中对应type表的信息
    @Override
    public Type getTypeById(Long id) {
        Type type = blogMapper.getTypeById(id);
        if (type!=null){
            return type;
        }
        return null;
    }
    //根据blog的id查找blog_tags表中对应tag表的信息
    @Override
    public Tag getTagsById(Long id) {
        Tag tags = blogMapper.getTagsById(id);
        if (tags!=null){
            return tags;
        }
        return null;
    }

    @Override
    public User getUserById(Long id) {
        User user = blogMapper.getUserById(id);
        if (user!=null){
            return user;
        }
        return null;
    }

    @Override
    public List<Comment> getCommentById(Long id) {

        List<Comment> commentList = blogMapper.getCommentById(id);
        if (commentList!=null){
            return commentList;
        }
        return null;
    }

    @Override
    public List<Long> getCommentsId(Long id) {
        List<Long> commentsId = blogMapper.getCommentsId(id);
        if (commentsId!=null){
            return commentsId;
        }
        return null;
    }


    //根据type的name查找对应type表的信息
    @Override
    public Type getTypeByName(String name) {
        Type type = blogMapper.getTypeByName(name);
        if (type!=null){
            return type;
        }
        return null;
    }

    //根据tag的name查找对应tag表的信息
    @Override
    public Tag getTagsByName(String name) {
        Tag tags = blogMapper.getTagsByName(name);
        if (tags!=null){
            return tags;
        }
        return null;
    }

    //向blog表中添加blog实体
    @Override
    public void saveBlog(Blog blog) {
        blogMapper.saveBlog(blog);
    }
    //根据新增表单提交的所有blog的属性查询该blog实体的id
    @Override
    public Long selectBlogId(String sql) {
        Long blogId = blogMapper.selectBlogId(sql);
        if (blogId!=null){
            return blogId;
        }
        return null;
    }

    @Override
    public void saveBlog_type(Long blogId, Long typeId) {
        blogMapper.saveBlog_type(blogId, typeId);
    }

    @Override
    public void saveBlog_tags(Long blogId, Long tagId) {
        blogMapper.saveBlog_tags(blogId, tagId);
    }

    @Override
    public void saveBlog_User(Long blogId, Long userId) {
        blogMapper.saveBlog_User(blogId,userId);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogMapper.updateBlog(blog);
    }

    @Override
    public void updateBlog_type(Long blogId, Long typeId) {
        blogMapper.updateBlog_type(blogId,typeId);
    }

    @Override
    public void updateBlog_tags(Long blogId, Long tagId) {
        blogMapper.updateBlog_tags(blogId,tagId);
    }


    //删除相应blog数据
    @Override
    public void deleteBlog_TypeById(Long blogId, Long typeId) {
        blogMapper.deleteBlog_TypeById(blogId, typeId);
    }

    @Override
    public void deleteBlog_TagsById(Long blogId, Long tagId) {
        blogMapper.deleteBlog_TagsById(blogId,tagId);
    }

    @Override
    public void deleteBlog_UserById(Long blogId, Long userId) {
        blogMapper.deleteBlog_UserById(blogId,userId);
    }

    @Override
    public void deleteBlog_CommentById(Long blogId, Long commentId) {

        blogMapper.deleteBlog_CommentById(blogId,commentId);
    }


    //查询符合要求的Blog的信息   采用模糊查询----->动态sql
    @Override
    public List<Long> findBlogIdList(String title, String typeOption, String recommend) {
        List<Long> blogList = blogMapper.findBlogIdList(title, typeOption, recommend);
        if (blogList!=null){
            return blogList;
        }
        return null;
    }


    @Override
    public void addLove(Long id) {
        blogMapper.addLove(id);
    }

    @Override
    public void addLikes(Long id) {
        blogMapper.addLikes(id);
    }

    @Override
    public List<Long> searchBlog(String search) {

        List<Long> blogsID = blogMapper.searchBlog(search);
        if (blogsID!=null){
            return blogsID;
        }
        return null;
    }


}
