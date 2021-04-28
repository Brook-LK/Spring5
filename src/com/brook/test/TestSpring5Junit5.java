package com.brook.test;

import com.brook.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:bean_IOC1.xml")
@SpringJUnitConfig(locations = "classpath:bean_IOC1.xml")  //@ExtendWith和@ContextConfiguration的组合注解
public class TestSpring5Junit5 {

    //整合Junit5,通过@ContextConfiguration配置注入
    @Autowired
    private User user;
    @Test
    public void test1() {
        System.out.println(user);
        user.add();
    }

}
