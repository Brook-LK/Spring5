package com.brook.entity;

public class Cycle {

    private String name;

    public Cycle() {
        System.out.println("1. 通过构造器创建bean实例（无参构造）");
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("2. 为bean的属性设置值和对其他bean的引用（调用set方法）");
    }

    //创建执行的初始化的方法
    public void init(){
        System.out.println("3. 调用bean的初始化方法（需要进行配置初始化方法）");
    }

    //创建销毁方法
    public void destroy(){
        System.out.println("5. 当容器关闭的时候，调用bean的销毁方法（需要配置销毁的方法）");
    }

    public void print(){
        System.out.println(name);
    }
}
