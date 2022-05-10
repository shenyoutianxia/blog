package com.company.project.auth.service;
import com.company.project.auth.model.*;
import com.company.project.common.service.Service;

import java.util.List;
import java.util.Map;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
public interface BlogService extends Service<Blog> {

    //根据blog的id查找blog_type表中对应type表的信息
    public Type getTypeById(Long id);
    //根据blog的id查找blog_tags表中对应tags表的信息
    public Tag getTagsById(Long id);
    //根据blog的id查找blog_user表中对应user表的信息
    public User getUserById(Long id);
    //根据blog的id查找blog_comment表中对应comment表的信息
    public List<Comment> getCommentById(Long id);

    //根据blog的id获取该实体下的comment的id
    public List<Long> getCommentsId(Long id);


    //根据type的name查找对应type表的信息
    public Type getTypeByName(String name);

    //根据tag的name查找对应tag表的信息
    public Tag getTagsByName(String name);

    //向blog表中添加blog实体并返回对应的id
    public void saveBlog(Blog blog);
    //向blog_type、blog_tag表中插入响应数据
    public void saveBlog_type(Long blogId,Long typeId);
    public void saveBlog_tags(Long blogId,Long tagId);
    public void saveBlog_User(Long blogId,Long userId);


    //修改所选id对应的实体blog信息
    public void updateBlog(Blog blog);
    //改变blog_type、blog_tag表中数据
    public void updateBlog_type(Long blogId,Long typeId);
    public void updateBlog_tags(Long blogId,Long tagId);

    //根据新增表单提交的所有blog的属性查询该blog实体的id
    public Long selectBlogId(String sql);

    //根据blog的id删除blog_type、blog_tags表和blog表中对应信息信息
    public void deleteBlog_TypeById(Long blogId,Long typeId);
    public void deleteBlog_TagsById(Long blogId,Long tagId);
    public void deleteBlog_UserById(Long blogId,Long userId);
    public void deleteBlog_CommentById(Long blogId,Long commentId);
    //查询符合要求的Blog的信息   采用模糊查询----->动态sql
    public List<Long> findBlogIdList(String title,String typeOption,String recommend);


    //更新点赞人数
    public void addLove(Long id);

    //更新查看人数
    public void addLikes(Long id);

    //主页面搜索
    public List<Long> searchBlog(String search);

}
