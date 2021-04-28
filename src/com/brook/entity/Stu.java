package com.brook.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Stu {

    private List<Book> books;
    private String[] course;
    private String name;
    private List<String> list;
    private Map<String,String> map;
    private Set<String> set;
    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "course=" + Arrays.toString(course) +
                ", name='" + name + '\'' +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                ", books=" + books +
                '}';
    }
}
