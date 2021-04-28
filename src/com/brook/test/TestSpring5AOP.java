package com.brook.test;

import com.brook.conf.SpringConfig;
import com.brook.dao.EmpDao;
import com.brook.dao.EmpDaoImpl;
import com.brook.dao.JDKProxy;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestSpring5AOP {

    //JDKProxy
    @Test
    public void test1(){
        JDKProxy.main(null);
    }

    //注解+xml开启和组件扫描
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_AOP1.xml");
        EmpDaoImpl empDao = context.getBean("empDaoImpl", EmpDaoImpl.class);
        //empDao.add(1);
        System.out.println(empDao.add(1));
        /**
         * 执行结果
         * 环绕之前
         * EmpDaoProxy.before()......
         * EmpDaoImpl.add()......
         * EmpDaoProxy.afterReturning()......
         * EmpDaoProxy.after()......
         * 环绕之后
         * null
         */
    }

    //注解开启AOP和组件扫描
    @Test
    public void test3(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        EmpDaoImpl empDao = context.getBean("empDaoImpl", EmpDaoImpl.class);
        System.out.println(empDao.add(1));
    }

}
