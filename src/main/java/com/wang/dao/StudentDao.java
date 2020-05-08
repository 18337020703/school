package com.wang.dao;

import com.wang.entity.Session;
import com.wang.entity.Student;
import org.springframework.stereotype.Component;

@Component
public interface StudentDao extends BaseDao<Student> {
    Student findAllByName(String username);
}
