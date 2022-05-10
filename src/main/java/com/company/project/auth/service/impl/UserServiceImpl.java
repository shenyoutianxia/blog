package com.company.project.auth.service.impl;

import com.company.project.auth.dao.UserMapper;
import com.company.project.auth.model.User;
import com.company.project.auth.service.UserService;
import com.company.project.common.exception.UserNotLoginException;
import com.company.project.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User checkUser(String Username, String Password) {
        User user = userMapper.checkUser(Username, Password);
        if (user!=null)
        {
            return user;
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);

    }



}
