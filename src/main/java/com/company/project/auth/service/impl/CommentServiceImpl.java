package com.company.project.auth.service.impl;

import com.company.project.auth.dao.CommentMapper;
import com.company.project.auth.model.Comment;
import com.company.project.auth.service.CommentService;
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
public class CommentServiceImpl extends AbstractService<Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public void saveComment(Comment comment) {

        commentMapper.saveComment(comment);
    }

    @Override
    public void addComment(Long blogId, Long commentId) {

        commentMapper.addComment(blogId,commentId);
    }

    @Override
    public void addCommentChildren(Long commentHostId, Long commentId) {
        commentMapper.addCommentChildren(commentHostId,commentId);
    }

    @Override
    public List<Long> getCommentsIds(Long commentId) {

        List<Long> ids = commentMapper.getCommentsIds(commentId);
        if (ids.size()>0){
            return ids;
        }
        return null;
    }
}
