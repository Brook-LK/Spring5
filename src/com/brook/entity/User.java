package com.brook.entity;

public class User {

    private Integer id;
    private String name;

    public String getName() {
        return name;
    }

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String name) {
        this.name = name;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void print(){
        System.out.println("user.print");
    }

    public void add(){
        System.out.println("add......");
    }
}
