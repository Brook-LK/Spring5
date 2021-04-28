package com.brook.test;

import com.brook.conf.SpringConfig;
import com.brook.dao.EmpDaoImpl;
import com.brook.dao.JDKProxy;
import com.brook.entity.Teacher;
import com.brook.service.TeacherService;
import com.brook.service.impl.TeacherServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestSpring5JDBC {

    //JDBC+xml
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_JDBC.xml");
        TeacherServiceImpl teacherService = context.getBean("teacherServiceImpl", TeacherServiceImpl.class);
        int n = teacherService.create(new Teacher(7,"找钱"));
        System.out.println(n);
    }

    //查询
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_JDBC.xml");
        TeacherServiceImpl teacherService = context.getBean("teacherServiceImpl", TeacherServiceImpl.class);
        int n = teacherService.findCount();     //返回总条数
        System.out.println(n);
        Teacher teacher1 = teacherService.findById(5);  //返回id为5的老师
        System.out.println(teacher1);
        List<Teacher> teacherList = teacherService.findAllList();  //以list返回全部老师
        System.out.println(teacherList);
    }

    //批量操作
    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_JDBC.xml");
        TeacherServiceImpl teacherService = context.getBean("teacherServiceImpl", TeacherServiceImpl.class);
        List<Object[]> teachers = new ArrayList<>();
        Object[] o1 = {9,"java"};
        Object[] o2 = {14,"python"};
        Object[] o3 = {10,"go"};
        teachers.add(o1);
        teachers.add(o2);
        teachers.add(o3);
        teacherService.batchAdd(teachers);     //批量添加,批量删除和，修改类似
    }

    //事务操作,注解方式配置
    @Test
    public void test4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_JDBC.xml");
        TeacherServiceImpl teacherService = context.getBean("teacherServiceImpl", TeacherServiceImpl.class);
        List<Object[]> teachers = new ArrayList<>();
        Object[] o1 = {9,"java"};
        Object[] o2 = {14,"python"};
        Object[] o3 = {10,"go"};
        teachers.add(o1);
        teachers.add(o2);
        teachers.add(o3);
        teacherService.transactional(teachers,new  Teacher(7,"找钱"));
    }

    //纯注解配置
    @Test
    public void test5(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TeacherServiceImpl teacherService = context.getBean("teacherServiceImpl", TeacherServiceImpl.class);
        int n = teacherService.create(new Teacher(7,"找多钱"));
        System.out.println(n);
    }


}
