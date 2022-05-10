package com.company.project.auth.web;

import com.company.project.auth.model.*;
import com.company.project.auth.service.*;
import com.company.project.common.result.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ResponseResult
@Controller
public class WebController {

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


    //登入页面
    @GetMapping(value = {"/"})
    public String loginPage(){
        return "login";
    }

    /**
     * 去index页面
     * 页表展示blog实体对象
     * @return
     */
    @GetMapping("/index.html")
    public String indexPage(@RequestParam(value = "pn",defaultValue = "1")Integer pn,String ajax, Model model){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        PageHelper.startPage(pn,4);

        List<Blog> blogList = blogService.findAll();

        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);

        for (Blog blog : pageInfo.getList()) {
            Long id = blog.getId();
            //根据blog的id查找blog_type表中对应type表的信息
            Type type = blogService.getTypeById(id);
            //根据blog的id查找blog_tags表中对应tag表的信息
            Tag tags = blogService.getTagsById(id);
            //根据blog的id查找blog_user表中对应user表的信息
            User user = blogService.getUserById(id);
            blog.setType(type);
            blog.setTags(tags);
            blog.setUser(user);
        }

        //展现type实体对象列表
        List<Type> typeList = typeService.findAll();
        for (Type type : typeList) {
            List<Blog> blogs = typeService.blog_typeNum(type.getId());
            type.setBlogs(blogs);
        }
        //展现tag实体对象列表
        List<Tag> tagList = tagService.findAll();
        for (Tag tag : tagList) {
            List<Blog> blogs = tagService.blog_tagNum(tag.getId());
            tag.setBlogs(blogs);
        }

        model.addAttribute("dateFormat",dateFormat);
        model.addAttribute("blogList",pageInfo);
        model.addAttribute("typeList",typeList);
        model.addAttribute("tagList",tagList);

        if ("true".equals(ajax)){
            return "blog/index::indexHtml";
        }
        return "blog/index";
    }


    /**
     * 去index-blog页面
     * @return
     */
    @GetMapping("/index-blog.html")
    public String indexBlog(){

        return "admin/index-blog";
    }


    //登入注销之后
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    //用户修改
    @GetMapping("/modify")
    public String modify(HttpSession session){

        return "/modifyINF";
    }

    /**
     * archives页面
     * @return
     */
    @GetMapping("/blog/archives.html")
    public String archives(){

        return "blog/archives";
    }



    /**
     * tags页面
     * @return
     */
    @GetMapping("/blog/tags.html")
    public String tags(){

        return "blog/tags";
    }

    /**
     * types页面
     * @return
     */
    @GetMapping("/blog/types.html")
    public String types(){

        return "blog/types";
    }

    /**
     * aboutMe页面
     * @return
     */
    @GetMapping("/blog/aboutMe.html")
    public String aboutMe(){

        return "blog/aboutMe";
    }


    /**
     * peopleBlog页面
     * 展示已有的评论
     * 添加更新查看人数(已完成)
     * @return
     */
    @GetMapping("/blog/peopleBlog.html{id}")
    public String peopleBlog(@PathVariable Long id, Model model){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Blog blog = blogService.findById(id);
        //更新查看人数
        blogService.addLikes(blog.getId());
        //根据blog的id查找blog_type表中对应type表的信息
        Type type = blogService.getTypeById(id);
        //根据blog的id查找blog_tags表中对应tag表的信息
        Tag tags = blogService.getTagsById(id);
        //根据blog的id查找blog_user表中对应user表的信息
        User user = blogService.getUserById(id);
        //根据blog的id查找blog_comment表中对应comment表的信息
        //List<Comment> commentList = blogService.getCommentById(id);

        List<Comment> commentList = new ArrayList<>();
        List<Long> commentsId = blogService.getCommentsId(id);
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

        blog.setType(type);
        blog.setTags(tags);
        blog.setUser(user);
        blog.setComments(commentList);
        model.addAttribute("dateFormat",dateFormat);
        model.addAttribute("blog",blog);
        return "blog/peopleBlog";
    }
    //采用递归判断是否有子评论
    public void CommentReplay(List<Comment> CommentChildren, Long id){

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



    /**
     * 在peopleBlog页面添加更新点赞数量
     * 添加更新点赞数量（采用ajax 已完成）
     */
    @GetMapping("/peopleBlog/addLove")
    public void addLove(@RequestParam Long id, HttpServletResponse response){

        blogService.addLove(id);
        Blog blog = blogService.findById(id);
        try {
            response.getWriter().println(blog.getLove());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @GetMapping("/blog/aboutPeople.html/{id}")
    public String aboutPeople(@PathVariable Long id,Model model){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Blog blog = blogService.findById(id);
        //根据blog的id查找blog_type表中对应type表的信息
        Type type = blogService.getTypeById(id);
        //根据blog的id查找blog_tags表中对应tag表的信息
        Tag tags = blogService.getTagsById(id);
        //根据blog的id查找blog_user表中对应user表的信息
        User user = blogService.getUserById(id);        //待解决的问题是该user的updateTime属性未映射到
        System.out.println(user);
        User user1 = userService.findById(user.getId());  //用本框架findById解决该问题   ----  后期维护该问题
        blog.setType(type);
        blog.setTags(tags);
        blog.setUser(user1);
        model.addAttribute("dateFormat",dateFormat);
        model.addAttribute("blog",blog);
        return "blog/aboutPeople";
    }



    /**
     * 主页面搜索引擎
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam(value = "pn",defaultValue = "1")Integer pn ,@RequestParam(value = "search") String search , Model model){

        //使用分页插件 展现blog实体对象列表
       // Page<Blog> pageBlog = new Page<>(pn,1);

       // QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();

        //queryWrapper.lambda().like(Blog::getTitle,search).or().like(Blog::getContent,search);

        //Page<Blog> blogList = blogService.page(pageBlog,queryWrapper);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Long> blog_ids = blogService.searchBlog(search);

        ArrayList<Blog> blogList = new ArrayList<>();

        PageHelper.startPage(pn,2);

        for (Long blog_id : blog_ids) {
            Blog blog = blogService.findById(blog_id);
            blogList.add(blog);
        }

        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);

        for (Blog blog : pageInfo.getList()) {

            Long id = blog.getId();
            //根据blog的id查找blog_type表中对应type表的信息
            Type type = blogService.getTypeById(id);
            //根据blog的id查找blog_tags表中对应tag表的信息
            Tag tags = blogService.getTagsById(id);
            //根据blog的id查找blog_user表中对应user表的信息
            User user = blogService.getUserById(id);
            blog.setType(type);
            blog.setTags(tags);
            blog.setUser(user);
        }
        if (blogList==null){
            model.addAttribute("message","为查询到响应结果...");
        }
        model.addAttribute("search",search);
        model.addAttribute("dateFormat",dateFormat);
        model.addAttribute("blogList",pageInfo);
        return "blog/search";
    }

}
