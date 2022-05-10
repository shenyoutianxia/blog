package com.company.project.auth.dao;

import com.company.project.auth.model.User;
import com.company.project.common.mapper.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

@Mapper
public interface UserMapper extends CrudMapper<User> {

    /**
     * 检测用户是否正确
     * @param Username
     * @param Password
     * @return
     */

    @Select("select * from user where username=#{Username} and password=#{Password}")
    public User checkUser(String Username, String Password);


    /**
     * 更改User信息
     * @param user
     */
    @Update("update user set avatar=#{avatar},password=#{password},nickname=#{nickname},email=#{email} where id = #{id}")
    public void updateUser(User user);

}
