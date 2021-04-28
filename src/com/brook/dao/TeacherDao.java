package com.brook.dao;

import com.brook.entity.Teacher;

import java.util.List;

public interface TeacherDao {

    int delete(Teacher teacher);

    int create(Teacher teacher);

    int findCount();

    Teacher findById(Integer id);

    List<Teacher> findAllList();

    void beachAdd(List<Object[]> teachers);
}
