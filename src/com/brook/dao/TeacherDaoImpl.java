package com.brook.dao;

import com.brook.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao{

    @Autowired(required=false)
    private JdbcTemplate jdbcTemplate;

    @Override
    public int delete(Teacher teacher) {
        return 0;
    }

    @Override
    public int create(Teacher teacher) {
        String sql = "insert into teacher value (?,?)";
        int n = 0;
        try{
            Object[] args = {teacher.getId(),teacher.getName()};
            //n = jdbcTemplate.update(sql,2,"李四");
            n = jdbcTemplate.update(sql,args);
        }catch (Exception e){
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int findCount() {
        String sql = "select count(1) from teacher";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public Teacher findById(Integer id) {
        String sql = "select * from teacher where id = (?)";

        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Teacher>(Teacher.class),id);
    }

    @Override
    public List<Teacher> findAllList() {
        String sql = "select * from teacher";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Teacher>(Teacher.class));
    }

    @Override
    public void beachAdd(List<Object[]> teachers) {
        String sql = "insert into teacher value (?,?)";
        int[] ints = jdbcTemplate.batchUpdate(sql,teachers);
        System.out.println(ints.toString());
    }
}
