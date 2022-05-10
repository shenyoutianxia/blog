package com.company.project.auth.web;


import com.company.project.auth.model.Blog;
import com.company.project.auth.model.Comment;
import com.company.project.auth.model.User;
import com.company.project.auth.service.*;
import com.company.project.common.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
@ResponseResult
@Controller
public class CommentController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;


    /**
     * blog评论
     * @param blogId
     * @param userId
     * @param content
     * @param model
     * @return
     */
    @PostMapping("/blog/comment")
    public String comment(@RequestParam("blogId") Long blogId, @RequestParam("userId") Long userId,
                          @RequestParam("content")String content, Model model){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Comment comment = new Comment();
        //获得登录者的实体类user
        User user = userService.findById(userId);
        if (user!=null){
            Date createTime = new Date();
            comment.setId(null);
            comment.setAvatar(user.getAvatar());
            comment.setContent(content);
            comment.setCreateTime(createTime);
            comment.setNickname(user.getNickname());
        }

        commentService.saveComment(comment);

        commentService.addComment(blogId,comment.getId());

        //根据blog的id查找blog_comment表中对应comment表的信息
       // List<Comment> commentList = blogService.getCommentById(blogId);
        List<Comment> commentList = new ArrayList<>();
        List<Long> commentsId = blogService.getCommentsId(blogId);
        for (Long cid : commentsId) {
            Comment commentServiceById = commentService.findById(cid);
            commentList.add(commentServiceById);
        }

        for (Comment commentHost : commentList) {
            List<Long> CommentChildrenIds = commentService.getCommentsIds(commentHost.getId());
            if (CommentChildrenIds != null){
                List<Comment> CommentChildren = new ArrayList<>();
                for (Long commentChildrenId : CommentChildrenIds) {
                    Comment commentOb = commentService.findById(commentChildrenId);
                    CommentChildren.add(commentOb);
                    //评论的评论
                    List<Long> serviceCommentsIds = commentService.getCommentsIds(commentChildrenId);
                    if (serviceCommentsIds!=null){
                        for (Long serviceCommentId : serviceCommentsIds) {
                            Comment comment1 = commentService.findById(serviceCommentId);
                            CommentChildren.add(comment1);
                            //采用递归判断是否有子评论
                            if (serviceCommentId!=null){
                                CommentReplay(CommentChildren,serviceCommentId);
                            }
                        }
                    }
                }
                commentHost.setReplayComments(CommentChildren);
            }
        }

        //获取当前blog实体
        Blog blog = blogService.findById(blogId);

        blog.setComments(commentList);
        model.addAttribute("dateFormat",dateFormat);
        model.addAttribute("blog",blog);

        return "blog/peopleBlog::discussHtml";
    }


    /**
     * comment回复
     * @param blogId
     * @param userId
     * @param content
     * @param model
     * @return
     */
    @PostMapping("/blog/replay")
    public String replay(@RequestParam("blogId") Long blogId, @RequestParam("userId") Long userId,
                         @RequestParam("content")String content,@RequestParam("commentId") Long commentHostId,
                         Model model){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Comment comment = new Comment();
        //获得登录者的实体类user
        User user = userService.findById(userId);
        if (user!=null){
            Date createTime = new Date();
            comment.setId(null);
            comment.setAvatar(user.getAvatar());
            comment.setContent(content);
            comment.setCreateTime(createTime);
            comment.setNickname(user.getNickname());

            //保存commentReplay的comment
            commentService.saveComment(comment);
            //存储commentReplay与commentReplay回复的comment的对应关系
            commentService.addCommentChildren(commentHostId,comment.getId());
        }

        //根据blog的id查找blog_comment表中对应comment表的信息
        //List<Comment> commentList = blogService.getCommentById(blogId);

        List<Comment> commentList = new ArrayList<>();
        List<Long> commentsId = blogService.getCommentsId(blogId);
        for (Long cid : commentsId) {
            Comment commentServiceById = commentService.findById(cid);
            commentList.add(commentServiceById);
        }

        for (Comment commentHost : commentList) {
            List<Long> CommentChildrenIds = commentService.getCommentsIds(commentHost.getId());
            if (CommentChildrenIds != null){
                List<Comment> CommentChildren = new ArrayList<>();
                for (Long commentChildrenId : CommentChildrenIds) {
                    Comment commentOb = commentService.findById(commentChildrenId);
                    CommentChildren.add(commentOb);
                    //评论的评论
                    List<Long> serviceCommentsIds = commentService.getCommentsIds(commentChildrenId);
                    if (serviceCommentsIds!=null){

                        for (Long serviceCommentId : serviceCommentsIds) {
                            Comment comment1 = commentService.findById(serviceCommentId);
                            CommentChildren.add(comment1);
                            //采用递归判断是否有子评论
                            if (serviceCommentId!=null){
                                CommentReplay(CommentChildren,serviceCommentId);
                            }
                        }
                    }
                }
                commentHost.setReplayComments(CommentChildren);
            }
        }

        //获取当前blog实体
        Blog blog = blogService.findById(blogId);

        blog.setComments(commentList);

        model.addAttribute("dateFormat",dateFormat);
        model.addAttribute("blog",blog);
        return "blog/peopleBlog::discussHtml";
    }

    //采用递归判断是否有子评论
    public void CommentReplay(List<Comment> CommentChildren,Long id){

        //判断该comment在comment_comments表中是否有对应的comment的id
        List<Long> commentsIds = commentService.getCommentsIds(id);
        if (commentsIds!=null){
            for (Long commentId : commentsIds) {
                Comment comment = commentService.findById(commentId);
                CommentChildren.add(comment);
                CommentReplay(CommentChildren,commentId);
            }
        }
    }
//    @PostMapping
//    public Comment add(@RequestBody Comment comment) {
//        commentService.save(comment);
//        return comment;
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        commentService.deleteById(id);
//    }
//
//    @PutMapping
//    public Comment update(@RequestBody Comment comment) {
//        commentService.update(comment);
//        return comment;
//    }
//
//    @GetMapping("/{id}")
//    public Comment detail(@PathVariable Long id) {
//        return commentService.findById(id);
//    }
//
//    @GetMapping
//    public PageInfo list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<Comment> list = commentService.findAll();
//         return new PageInfo(list);
//    }
}
