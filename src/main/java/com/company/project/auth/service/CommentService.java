package com.company.project.auth.service;
import com.company.project.auth.model.Comment;
import com.company.project.common.service.Service;

import java.util.List;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
public interface CommentService extends Service<Comment> {

    //在访问blog页面中评论  comment,blog_comment表中更新信息
    public void saveComment(Comment comment);
    public void addComment(Long blogId,Long commentId);

    public void addCommentChildren(Long commentHostId,Long commentId);


    //根据commentHost的id查询comment_comments表中对应的comments的集合ids
    public List<Long> getCommentsIds(Long commentId);
}
