package com.company.project.auth.web;

import com.company.project.auth.model.User;
import com.company.project.auth.service.UserService;
import com.company.project.common.result.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static com.company.project.common.util.FileUtils.delAllFile;

/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
@ResponseResult
@Controller
public class UserController {

    /**上传地址*/
    @Value("${file.upload.path}")
    private String filePath;

    @Resource
    private UserService userService;

    /**
     * 登录校验
     * @param Username
     * @param Password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String Username,
            @RequestParam("password") String Password,
            HttpSession session,
            RedirectAttributes attributes){

        User user = userService.checkUser(Username, Password);
        if(user!=null) {
            session.setAttribute("user",user);
            //登入成功，重定向到index-blog.html  防止刷新表单重复提交
            return "redirect:index-blog.html";
        }

        else {
            attributes.addFlashAttribute("message","用户名或密码错误!");
            return "redirect:/";
        }
    }

    /**
     * User信息更改
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public void updateUser(User user,HttpSession session,HttpServletResponse response) throws IOException {

        if (user!=null){
            Date updateTime = new Date();
            user.setUpdateTime(updateTime);
            userService.updateUser(user);
            session.setAttribute("user",user);
            response.getWriter().println("/index-blog.html");
        }
    }


    /**
     * User头像更改
     * @param file
     * @return
     */
    @PostMapping("/updateUserAvatar")
    public void updateUserAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取图片的名字
        String imgName=file.getOriginalFilename();
        //获取Mime类型,验证是否为图片类型
        String mimeType = request.getServletContext().getMimeType(imgName);
        if(mimeType==null || !mimeType.contains("image")){
            response.getWriter().println("img/no.jpg");   //不是图片，直接返回默认图片
        }

        //判断当前请求来源于哪个浏览器
        String header = request.getHeader("User-Agent");  //   获取浏览器的信息
        if(header.contains("Chrome")){
            //Chrome浏览器返回的图片名字是绝对路径，所以我们要把图片名字提取出来
            imgName=imgName.substring(imgName.lastIndexOf('\\')+1, imgName.length());
        }
        //临时存放图片的路径
        String fileName=filePath;
        //给图片名字加个防止重复的前缀
        imgName= UUID.randomUUID().toString().replace("-","")+"_"+imgName;
        //定义文件
        File newFile=new File(fileName,imgName);
        //清空文件的内容，因为是临时文件
        //这次用到了工具类清理文件，代码下面可见
        //delAllFile(newFile.getParentFile().getPath());

        //检测文件夹是否存在
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        //返回给前端的地址
        //之所以返回这个地址，因为我配置了资源重定向，下面代码可见
        String path="/avatar/"+imgName;
        //图片上传到文件中
        file.transferTo(newFile);
        response.getWriter().println(path);
    }



//    @PostMapping
//    public User add(@RequestBody User user) {
//        userService.save(user);
//        return user;
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        userService.deleteById(id);
//    }
//
//    @PutMapping
//    public User update(@RequestBody User user) {
//        userService.update(user);
//        return user;
//    }
//
//    @GetMapping("/{id}")
//    public User detail(@PathVariable Long id) {
//        return userService.findById(id);
//    }
//
//    @GetMapping
//    public PageInfo list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<User> list = userService.findAll();
//         return new PageInfo(list);
//    }
}
