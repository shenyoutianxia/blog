package com.company.project.auth.web;

import com.company.project.auth.model.Blog;
import com.company.project.auth.model.Type;
import com.company.project.auth.service.BlogService;
import com.company.project.auth.service.TypeService;
import com.company.project.common.result.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
public class TypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
     * typesController页面
     * @return
     */
    @GetMapping("/admin/typesController.html")
    public String typesController(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //从数据库中查询所有记录
//        List<Type> types = typeService.getTypes();
//        model.addAttribute("types",types);

        //查询分页----------需要配置分页插件使用
//        Page<Type> typesPage = new Page<>(pn, 5);
//
//        Page<Type> types = typeService.page(typesPage,null);  //typeService.page(typesPage)继承IService<T>方法

        PageHelper.startPage(pn,5);

        List<Type> typeList = typeService.findAll();

        PageInfo<Type> pageInfo = new PageInfo<>(typeList);

        model.addAttribute("types",pageInfo);

        return "admin/typesController";
    }

    /**
     * typesController页面实现翻页效果
     * @return
     */
    @GetMapping("/admin/typesControllerPages.html")
    public String typesControllerPages(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //从数据库中查询所有记录
//        List<Type> types = typeService.getTypes();
//        model.addAttribute("types",types);

        //查询分页----------需要配置分页插件使用
//        Page<Type> typesPage = new Page<>(pn, 5);
//
//        Page<Type> types = typeService.page(typesPage,null);  //typeService.page(typesPage)继承IService<T>方法

        PageHelper.startPage(pn,5);

        List<Type> typeList = typeService.findAll();

        PageInfo<Type> pageInfo = new PageInfo<>(typeList);

        model.addAttribute("types",pageInfo);

        return "admin/typesController::tobody";
    }


    /**
     * typesControllerInput页面
     * @return
     */
    @GetMapping("/admin/typesControllerInput.html")
    public String typesControllerInput(){

        return "admin/typesControllerInput";
    }

    /**
     * typesControllerUpdate页面
     * @return
     */
    @GetMapping("/admin/typesControllerUpdate.html/{id}")
    public String typesControllerUpdate(@PathVariable Long id, Model model){
        Type type = typeService.getType(id);
        model.addAttribute("updateType",type);
        return "admin/typesControllerUpdate";
    }

    /**
     * deleteType删除功能实现
     * 若有实体blog对象与Type实体对象关联 无法删除该Type实体对象
     * @return
     */
    @GetMapping("/deleteType")
    public void deleteType(@RequestParam Long id, RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {

        //在blog_type表中查询tag的id所对应的blog实体id
        Long blogId = typeService.selectBlogId(id);
        //获取出对应的blog实体对象 并判断是否存在
        Blog blog = blogService.findById(blogId);
        if(blog == null){
            typeService.deleteType(id);
        }
        else {
            redirectAttributes.addFlashAttribute("message","Sorry 该条记录已有关联性,不能删除...");
        }

        response.getWriter().println("/admin/typesController.html");
    }

    /**
     * 来自typesControllerInput的表单请求 ,添加信息
     * @return
     */
    @PostMapping("/types")
    public String typesSave(@RequestParam("typeName") String typeName){
        typeService.saveType(typeName);
        return "redirect:admin/typesController.html";
    }

    /**
     * updateType修改功能实现
     * @return
     */
    @PostMapping("/updateType/{id}")
    public String updateType(@PathVariable Long id,Type type){
        typeService.updateType(id,type);
        return "redirect:/admin/typesController.html";
    }

//    @PostMapping
//    public Type add(@RequestBody Type type) {
//        typeService.save(type);
//        return type;
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        typeService.deleteById(id);
//    }
//
//    @PutMapping
//    public Type update(@RequestBody Type type) {
//        typeService.update(type);
//        return type;
//    }
//
//    @GetMapping("/{id}")
//    public Type detail(@PathVariable Long id) {
//        return typeService.findById(id);
//    }
//
//    @GetMapping
//    public PageInfo list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<Type> list = typeService.findAll();
//         return new PageInfo(list);
//    }
}
