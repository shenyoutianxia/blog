package com.company.project.auth.dao;

import com.company.project.auth.model.Comment;
import com.company.project.common.mapper.CrudMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends CrudMapper<Comment> {

    @Insert("insert into comment values(null,#{avatar},#{content},#{createTime},#{nickname})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public void saveComment(Comment comment);

    @Insert("insert into blog_comment values(#{blogId},#{commentId})")
    public void addComment(Long blogId,Long commentId);

    @Insert("insert into comment_comments values(#{commentHostId},#{commentId})")
    public void addCommentChildren(Long commentHostId,Long commentId);


    //根据commentHost的id查询comment_comments表中对应的comments的集合ids
    @Select("select comment_id from comment_comments where commentHost_id = #{commentId}")
    public List<Long> getCommentsIds(Long commentId);
}