package com.brook.test;

import com.brook.entity.Teacher;
import com.brook.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)     //加入测试框架版本
@ContextConfiguration("classpath:bean_IOC1.xml")       //加载配置文件
public class TestSpring5NEW {

    //函数式风格创建对象，交给Spring进行管理
    @Test
    public void test1(){
        //创建GenericApplicationContext对象
        GenericApplicationContext context = new GenericApplicationContext();
        //调用context的方法对象注册
        context.refresh();
        context.registerBean(Teacher.class, () -> new Teacher());
        //获取在spring注册的对象
        ///Teacher teacher = (Teacher) context.getBean("teacher");    //无法通过类名获取，可以通过全路径获取
        Teacher teacher = (Teacher) context.getBean("com.brook.entity.Teacher");

        System.out.println(teacher);
    }
    @Test
    public void test2(){
        //创建GenericApplicationContext对象
        GenericApplicationContext context = new GenericApplicationContext();
        //调用context的方法对象注册
        context.refresh();
        context.registerBean("teacher1",Teacher.class, () -> new Teacher());   //可以通过指定名字获取
        //获取在spring注册的对象
        Teacher teacher = (Teacher) context.getBean("teacher1");

        System.out.println(teacher);
    }

    //整合Junit4,通过@ContextConfiguration配置注入
    @Autowired
    private User user;
    @Test
    public void test3() {
        System.out.println(user);
        user.add();
    }

}
