package com.brook.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JDKProxy {

    public static void main(String[] args) {
        //创建接口实现类代理对象
        Class[] interfaces = {UserDao.class};
        /*Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new InvocationHandler() {
            //直接写匿名内部类
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });*/
        UserDao userDao = (UserDao)Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(new UserDaoImpl()));
        System.out.println(userDao.add(1,3));
    }
}

//创建代理对象代码
class UserDaoProxy implements InvocationHandler{

    private Object obj;

    //1、把创建的是谁的代理对象，把谁传进来，知道原来干了啥
//    public UserDaoProxy(UserDaoImpl userDao){
    public UserDaoProxy(Object obj){
        this.obj = obj;
    }

    //增强的逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法之前执行
        System.out.println("增强方法之前执行"+method.getName() + " 传递的参数: "+ Arrays.toString(args));

        //被增强的方法
        Object res = method.invoke(obj,args);

        //方法之后执行
        System.out.println("增强方法之后执行"+  method.getName());
        return res;
    }
}
