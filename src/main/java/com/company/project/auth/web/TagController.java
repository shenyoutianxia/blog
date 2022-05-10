package com.company.project.auth.web;
import com.company.project.auth.model.Blog;
import com.company.project.auth.model.Tag;
import com.company.project.auth.service.BlogService;
import com.company.project.auth.service.TagService;
import com.company.project.common.result.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class TagController {

    @Resource
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    /**
     * tagsController页面
     * @return
     */
    @GetMapping("/admin/tagsController.html")
    public String tagsController(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //从数据库中查询所有记录
//        List<Tag> tags = tagService.getTags();
//        model.addAttribute("tags",tags);

        //查询分页----------需要配置分页插件使用
//        Page<Tag> tagsPage = new Page<>(pn, 5);
//
//        Page<Tag> tags = tagService.page(tagsPage,null);  //tagService.page(tagsPage)继承IService<T>方法

        PageHelper.startPage(pn,5);

        List<Tag> tagList = tagService.findAll();

        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);

        model.addAttribute("tags",pageInfo);

        return "admin/tagsController";
    }

    /**
     * tagsController页面实现翻页效果
     * @return
     */
    @GetMapping("/admin/tagsControllerPages.html")
    public String tagsControllerPages(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){

        PageHelper.startPage(pn,5);

        List<Tag> tagList = tagService.findAll();

        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);

        model.addAttribute("tags",pageInfo);
        return "admin/tagsController::tobody";
    }


    /**
     * tagsControllerInput页面
     * @return
     */
    @GetMapping("/admin/tagsControllerInput.html")
    public String tagsControllerInput(){

        return "admin/tagsControllerInput";
    }

    /**
     * tagsControllerUpdate页面
     * @return
     */
    @GetMapping("/admin/tagsControllerUpdate.html")
    public String tagsControllerUpdate(@RequestParam Long id, Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute("updateTag",tag);
        return "admin/tagsControllerUpdate";
    }

    /**
     * deleteTag删除功能实现
     * @return
     */
    @GetMapping("/deleteTag")
    public void deleteTag(@RequestParam Long id, RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
        //在blog_type表中查询tag的id所对应的blog实体id
        Long blogId = tagService.selectBlogId(id);
        //获取出对应的blog实体对象 并判断是否存在
        Blog blog = blogService.findById(blogId);
        if(blog == null){
            tagService.deleteTag(id);
        }
        else{
            redirectAttributes.addFlashAttribute("message","Sorry 该条记录已有关联性,不能删除...");
        }

        response.getWriter().println("/admin/tagsController.html");

    }

    /**
     * 来自tagsControllerInput的表单请求 ,添加信息
     * @return
     */
    @PostMapping("/tags")
    public String tagsSave(@RequestParam("tagName") String tagName){
        tagService.saveTag(tagName);
        return "redirect:admin/tagsController.html";
    }

    /**
     * updateType修改功能实现
     * @return
     */
    @PostMapping("/updateTag")
    public String updateTag(@RequestParam Long id,Tag tag){
        tagService.updateTag(id,tag);
        return "redirect:/admin/tagsController.html";
    }
//    @PostMapping
//    public Tag add(@RequestBody Tag tag) {
//        tagService.save(tag);
//        return tag;
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        tagService.deleteById(id);
//    }
//
//    @PutMapping
//    public Tag update(@RequestBody Tag tag) {
//        tagService.update(tag);
//        return tag;
//    }
//
//    @GetMapping("/{id}")
//    public Tag detail(@PathVariable Long id) {
//        return tagService.findById(id);
//    }
//
//    @GetMapping
//    public PageInfo list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<Tag> list = tagService.findAll();
//         return new PageInfo(list);
//    }
}
