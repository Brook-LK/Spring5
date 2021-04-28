package com.brook.dao;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect     //生成代理对象
public class EmpDaoProxy {

    //重用切入点
    @Pointcut(value = "execution(* com.brook.dao.EmpDaoImpl.add(..))")               //切入点注解
    public void pointDemo(){

    }

    //前置通知
    @Before(value = "execution(* com.brook.dao.EmpDaoImpl.add(..))")
    @Order(1)       //当有多个同一种增强类的时候可以设置优先级增加注解：@Order(数值)，数值越小，优先级越高，可以加在方法中，也可以加在类上
    public void before(){
        System.out.println("EmpDaoProxy.before()......");
    }

    //最终通知
    @After(value = "pointDemo()")       //pointDemo()表示把切入点的表达式给它
    public void after(){
        System.out.println("EmpDaoProxy.after()......");
    }

    //后置通知（有异常的时候不执行）
    @AfterReturning(value = "execution(* com.brook.dao.EmpDaoImpl.add(..))")
    public void afterReturning(){
        System.out.println("EmpDaoProxy.afterReturning()......");
    }

    //异常通知
    @AfterThrowing(value = "execution(* com.brook.dao.EmpDaoImpl.add(..))")
    public void afterThrowing(){
        System.out.println("EmpDaoProxy.afterThrowing()......");
    }

    //环绕通知
    @Around(value = "execution(* com.brook.dao.EmpDaoImpl.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕之前");
        //被增强的方法执行
        proceedingJoinPoint.proceed();
        System.out.println("环绕之后");
    }

}
