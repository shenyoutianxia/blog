package com.company.project.auth.web;
import com.company.project.auth.model.*;
import com.company.project.auth.service.BlogService;
import com.company.project.auth.service.TagService;
import com.company.project.auth.service.TypeService;
import com.company.project.common.result.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
@ResponseResult
@Controller
public class BlogController {

    @Resource
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 查询所有Blog实体 显示在blogController页面
     * @return
     */
    @GetMapping("/blogController.html")
    public String getController(@RequestParam(value = "pn",defaultValue = "1")Integer pn,String ajax, Model model) {

        //从数据库中获取types展现在blogController页面
        List<Type> typeList = typeService.findAll();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        PageHelper.startPage(pn,4);

        List<Blog> blogList= blogService.findAll();

        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);

        for (Blog blog : pageInfo.getList()) {
            Long id = blog.getId();
            //根据blog的id查找blog_type表中对应type表的信息
            Type type = blogService.getTypeById(id);
            blog.setType(type);
        }
        model.addAttribute("typeList",typeList);
        model.addAttribute("dateFormat",dateFormat);
        model.addAttribute("blogList",pageInfo);
        if ("true".equals(ajax)){
            return "admin/blogController::tobody";
        }
        return "admin/blogController";
    }


    @GetMapping("/admin/blogControllerInputPage.html")
    public void blogControllerInputPage(HttpServletResponse response) throws IOException {

        response.getWriter().println("/admin/blogControllerInput.html");
    }
    /**
     * 去blogControllerInput页面
     * @return
     */
    @GetMapping("/admin/blogControllerInput.html")
    public String blogControllerInput(Model model){

        //从数据库中获取types展现在blogController页面
        List<Type> typeList = typeService.findAll();
        //从数据库中获取tags展现在blogController页面
        List<Tag> tagList = tagService.findAll();
        model.addAttribute("typeList",typeList);
        model.addAttribute("tagList",tagList);
        return "admin/blogControllerInput";
    }

    /**
     * 添加实体blog
     */
    @PostMapping("/saveBlog")
    public void saveBlog(Blog blog,HttpServletResponse response,@RequestParam String typeName,@RequestParam String tagsName,@RequestParam Long userId) throws IOException {

        Date data= new Date();//blog实体的创建时间
        blog.setCreateTime(data);
        //保存blog实体
        blogService.saveBlog(blog);
        //获取typeName、tagsName对应的type、tags的id
        Type type = blogService.getTypeByName(typeName);
        blogService.saveBlog_type(blog.getId(),type.getId());
        Tag tags = blogService.getTagsByName(tagsName);
        blogService.saveBlog_tags(blog.getId(),tags.getId());
        blogService.saveBlog_User(blog.getId(),userId);

        response.getWriter().println("/blogController.html");
    }

    /**
     * 查询符合要求的Blog的信息    用于ajax请求
     * @return
     */
    @PostMapping("/searchBlog")
    public String searchBlog(@RequestParam(defaultValue = "null") String title,
                             @RequestParam String typeOption,
                             @RequestParam(defaultValue = "false") String recommend,
                             @RequestParam(defaultValue = "false") String ajax,
                             Model model){


        //根据blog的name查找对应type表的信息
        Type type = blogService.getTypeByName(typeOption);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        model.addAttribute("dateFormat",dateFormat);
        //展现type实体对象列表
        List<Type> typeList = typeService.findAll();

        model.addAttribute("typeList",typeList);

        if (type!=null){

            //采用模糊查询----->动态sql
//            Map<String,String> map = new HashMap<>();
//            map.put("title",title);
//            map.put("recommend",recommend);
//            String sql = "select blog.id,blog.appreciation, blog.commentabled, blog.content, blog.create_time, blog.first_picture," +
//                    " blog.address, blog.recommend, blog.share_statement, blog.title, blog.update_time, blog.views " +
//                    "from blog,t_type,blog_type where  blog.id = blog_type.blog_id AND t_type.id = blog_type.type_id and t_type.name= "+"\'"+typeOption+"\' " ;
//            StringBuilder sb = new StringBuilder(sql);
//            //遍历map
//            Set<String> keySet = map.keySet();
//            for (String key : keySet) {
//                String value = map.get(key);
//                //value有值
//                if (value != null && !"".equals(value)){
//                    if ("title".equals(key)){
//                        sb.append(" and "+"blog."+key+" like "+"CONCAT('%',"+"\'"+value+"\'"+",'%')  " );
//                    }
//                    sb.append(" and "+"blog."+key+" = "+"\'"+value+"\'" );
//                }
//            }
//            String SQL = sb.toString();
            List<Long> blogIdList = blogService.findBlogIdList(title, typeOption, recommend);

            ArrayList<Blog> searchBlogList = new ArrayList<>();

            for (Long blogId : blogIdList) {
                searchBlogList.add(blogService.findById(blogId));
            }

            for (Blog blog : searchBlogList) {
                blog.setType(type);
            }
            if (searchBlogList.size()>0){
                model.addAttribute("searchBlogList",searchBlogList);
            }
            else {
                model.addAttribute("searchMsg","未查询到结果...");
            }
        }
        if ("true".equals(ajax)){
            return "admin/searchBlog::searchBlog";
        }
        return "admin/searchBlog";
    }


    @GetMapping("/admin/blogControllerUpdatePage.html")
    public void blogControllerUpdatePage(@RequestParam Long id,HttpServletResponse response) throws IOException {

        response.getWriter().println("/admin/blogControllerUpdate.html/"+id);
    }
    /**
     * 去blogControllerUpdate页面
     * @return
     */
    @GetMapping("/admin/blogControllerUpdate.html/{id}")
    public String blogControllerUpdate(@PathVariable Long id,Model model){

        List<Type> typeList = typeService.findAll();

        List<Tag> tagList = tagService.findAll();

        //获取对应id的blog对象
        Blog blog = blogService.findById(id);
        Type type = blogService.getTypeById(id);
        Tag tag = blogService.getTagsById(id);
        model.addAttribute("blog",blog);
        model.addAttribute("type",type);
        model.addAttribute("tag",tag);
        model.addAttribute("typeList",typeList);
        model.addAttribute("tagList",tagList);
        return "admin/blogControllerUpdate";
    }

    /**
     * 修改blog实体信息
     */
    @PostMapping("/updateBlog")
    public void updateBlog(
                               Blog blog,HttpServletResponse response, @RequestParam String typeName,@RequestParam String tagsName
    ) throws IOException {

        //修改blog表中blog实体
        Date data= new Date();//blog实体的修改时间
        blog.setUpdateTime(data);
//        String updateSql = "update blog set appreciation="+"\'"+appreciation+"\'"+","+"commentabled="+"\'"+commentabled+"\'"+","+
//                "content="+"\'"+content+"\'"+","+"share_statement="+"\'"+shareStatement+"\'"+","+"recommend="+"\'"+recommend+"\'"+","+
//                "address="+"\'"+address+"\'"+","+"content="+"\'"+content+"\'"+","+"title="+"\'"+title+"\'"+","+"views="+"\'"+views+"\'"+","+
//                "update_time="+"\'"+updateTime+"\'"+" where id="+id;

        //修改所选id对应的实体blog信息
        blogService.updateBlog(blog);
        //获取typeName、tagsName对应的type、tags的id
        Type type = blogService.getTypeByName(typeName);
        blogService.updateBlog_type(blog.getId(),type.getId());
        Tag tags = blogService.getTagsByName(tagsName);
        blogService.updateBlog_tags(blog.getId(),tags.getId());

        response.getWriter().println("/blogController.html");
    }

    /**
     * 通过id删除信息
     * @param id
     */
    @GetMapping("/deleteBlog")
    public void deleteBlog(@RequestParam Long id,HttpServletResponse response) throws IOException {
        //根据blog的id查找blog_type、blog_type,blog_user,blog_comment表中对应type、tag,user,comment表的信息
        Type type = blogService.getTypeById(id);
        Tag tag = blogService.getTagsById(id);
        User user = blogService.getUserById(id);
        List<Comment> commentList = blogService.getCommentById(id);
        //删除blog_type、blog_tags表中对应数据
        blogService.deleteBlog_TypeById(id,type.getId());
        blogService.deleteBlog_TagsById(id,tag.getId());
        blogService.deleteBlog_UserById(id,user.getId());
        for (Comment comment : commentList) {
            blogService.deleteBlog_CommentById(id,comment.getId());
        }
        //删除blog中对应数据
        blogService.deleteById(id);
        response.getWriter().println("/blogController.html");
    }

//    @PostMapping
//    public Blog add(@RequestBody Blog blog) {
//        blogService.save(blog);
//        return blog;
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        blogService.deleteById(id);
//    }
//
//    @PutMapping
//    public Blog update(@RequestBody Blog blog) {
//        blogService.update(blog);
//        return blog;
//    }
//
//    @GetMapping("/{id}")
//    public Blog detail(@PathVariable Long id) {
//        return blogService.findById(id);
//    }
//
//    @GetMapping("/blog")
//    public PageInfo list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<Blog> list = blogService.findAll();
//         return new PageInfo(list);
//    }
}
