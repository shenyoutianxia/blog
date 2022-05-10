package com.company.project.auth.service;
import com.company.project.auth.model.User;
import com.company.project.common.service.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.util.Date;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
public interface UserService extends Service<User> {

    /**
     * 检测用户是否正确
     * @param Username
     * @param Password
     * @return
     */
    public User checkUser(String Username,String Password);


    public void updateUser(User user);


}
