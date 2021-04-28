package com.brook.service.impl;

import com.brook.dao.TeacherDao;
import com.brook.entity.Teacher;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl {

    @Autowired
    private TeacherDao teacherDao;


    //@Override
    public int delete(Teacher teacher) {
        return teacherDao.delete(teacher);
    }

    //@Override
    public int create(Teacher teacher) {
        return teacherDao.create(teacher);
    }

    public int findCount() {
        return teacherDao.findCount();
    }

    public Teacher findById(Integer id) {
        return teacherDao.findById(id);
    }

    public List<Teacher> findAllList(){
        return teacherDao.findAllList();
    }

    public void batchAdd(List<Object[]> teachers) {
        teacherDao.beachAdd(teachers);
    }

    @Transactional(readOnly = false)
    public void transactional(List<Object[]> teachers,Teacher teacher){
        teacherDao.beachAdd(teachers);
        int i = 2/0;
        teacherDao.create(teacher);


    }
}
