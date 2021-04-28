package com.brook.dao;

import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {

    @Override
    public void test() {
        System.out.println("UserDao.UserDaoImpl.test()......");
    }

    @Override
    public int add(int a,int b) {
        System.out.println("UserDao.UserDaoImpl.add()......");
        return a+b;
    }

    @Override
    public String str(String str) {
        return str;
    }
}
