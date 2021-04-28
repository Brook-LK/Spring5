package com.brook.util;

import com.brook.entity.User;
import org.springframework.beans.factory.FactoryBean;

public class MyBean implements FactoryBean {
//public class MyBean implements FactoryBean<User> {        //也可以规定返回的类型为User
    @Override
    public Object getObject() throws Exception {
        User user = new User();
        user.setId(12);
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
