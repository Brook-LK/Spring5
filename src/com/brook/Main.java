package com.brook;

import com.brook.entity.Car;
import com.brook.entity.Ioc;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {


        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        // 注册配置
        ctx.scan(Ioc.class.getPackage().getName());

        // 启动这个容器
        ctx.refresh();

        // 依赖查找
        final Car bean = ctx.getBean(Car.class);

        System.out.println(bean);

    }





}


