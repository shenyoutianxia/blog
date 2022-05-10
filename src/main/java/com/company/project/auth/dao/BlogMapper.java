package com.company.project.auth.dao;

import com.company.project.auth.model.*;
import com.company.project.common.mapper.CrudMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper extends CrudMapper<Blog> {


    //根据blog的id查找blog_type表中对应type表的信息
    @Select("select t_type.id ,t_type.name from blog,t_type,blog_type where  blog.id = blog_type.blog_id AND t_type.id = blog_type.type_id and blog.id=#{id}")
    public Type getTypeById(Long id);
    //根据blog的id查找blog_tags表中对应tags表的信息
    @Select("select tag.id ,tag.name from blog,tag,blog_tags where  blog.id = blog_tags.blogs_id AND tag.id = blog_tags.tags_id and blog.id=#{id}")
    public Tag getTagsById(Long id);
    //根据blog的id查找blog_user表中对应user表的信息
    @Select("select user.id,user.avatar,user.create_time,user.email,user.nickname,user.password,user.type,user.update_time,user.username,user.description from blog,user,blog_user where  blog.id = blog_user.blog_id AND user.id = blog_user.user_id and blog.id=#{id}")
    public User getUserById(Long id);
    //根据blog的id查找blog_comment表中对应comment表的信息
    @Select("select comment.id,comment.avatar,comment.content,comment.create_time,comment.nickname from blog,comment,blog_comment where  blog.id = blog_comment.blog_id AND comment.id = blog_comment.comment_id and blog.id=#{id}")
    public List<Comment> getCommentById(Long id);

    //根据blog的id获取该实体下的comment的id
    @Select("select comment.id from blog,comment,blog_comment where  blog.id = blog_comment.blog_id AND comment.id = blog_comment.comment_id and blog.id=#{id}")
    public List<Long> getCommentsId(Long id);


    //根据type的name查找对应type表的信息
    @Select("select * from t_type where name = #{name}")
    public Type getTypeByName(String name);

    //根据tag的name查找对应tag表的信息
    @Select("select * from tag where name = #{name}")
    public Tag getTagsByName(String name);



    //向blog表中添加blog实体
    public void saveBlog(Blog blog);

    //向blog_type、blog_tag表中插入响应数据
    @Insert("insert into blog_type values(#{blogId},#{typeId})")
    public void saveBlog_type(Long blogId,Long typeId);
    @Insert("insert into blog_tags values(#{blogId},#{tagId})")
    public void saveBlog_tags(Long blogId,Long tagId);
    @Insert("insert into blog_user values(#{blogId},#{userId})")
    public void saveBlog_User(Long blogId,Long userId);


    //修改所选id对应的实体blog信息
//    @Update("${updateSql}")
//    public void updateBlog(String updateSql);
    public void updateBlog(Blog blog);

    //改变blog_type、blog_tag表中数据
    @Update("update blog_type set type_id=#{typeId} where blog_id=#{blogId}")
    public void updateBlog_type(Long blogId,Long typeId);
    @Update("update blog_tags set tags_id=#{tagId} where blogs_id=#{blogId}")
    public void updateBlog_tags(Long blogId,Long tagId);



    //根据新增表单提交的所有blog的属性查询该blog实体的id
    @Select("${sql}")
    public Long selectBlogId(String sql);



    //根据blog的id删除blog_type、blog_tags表和blog表中对应信息信息
    @Delete("delete from blog_type where blog_id = #{blogId} and type_id = #{typeId}")
    public void deleteBlog_TypeById(Long blogId,Long typeId);
    @Delete("delete from blog_tags where blogs_id = #{blogId} and tags_id = #{tagId}")
    public void deleteBlog_TagsById(Long blogId,Long tagId);
    @Delete("delete from blog_user where blog_id = #{blogId} and user_id = #{userId}")
    public void deleteBlog_UserById(Long blogId,Long userId);
    @Delete("delete from blog_comment where blog_id = #{blogId} and comment_id = #{commentId}")
    public void deleteBlog_CommentById(Long blogId,Long commentId);

    //查询符合要求的Blog的信息   采用模糊查询----->动态sql
//    @Select("${sb}")
//    public List<Blog> findBlogList(String sb);
    public List<Long> findBlogIdList(String title,String typeOption,String recommend);


    //更新点赞人数
    @Update("update blog set love = love+1 where id = #{id}")
    public void addLove(Long id);

    //更新查看人数
    @Update("update blog set likes = likes+1 where id = #{id}")
    public void addLikes(Long id);

    //主页面搜索
    @Select("select * from blog where title like CONCAT('%',#{search},'%') or content like CONCAT('%',#{search},'%')")
    public List<Long> searchBlog(String search);

}