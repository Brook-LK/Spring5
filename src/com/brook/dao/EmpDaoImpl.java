package com.brook.dao;

import org.springframework.stereotype.Component;

@Component
public class EmpDaoImpl{

    public Object add(int a) {      //这里返回规定为int的时候，代理的时候会报错，这样执行返回null ^>^
        System.out.println("EmpDaoImpl.add()......");
        return a+1;
        //return a/0;
    }
}
