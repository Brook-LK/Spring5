package com.brook.service;

import com.brook.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component     //默认("userService")创建bean的id为类名首字母小写的id
public class UserService {

    @Autowired
    //通过Spring配置，创建UserDao对象
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void test(){
        System.out.println("UserService.test()......");

        //创建UserDao对象
//        UserDao userDao = new UserDaoImpl();
        userDao.test();
    }
}
