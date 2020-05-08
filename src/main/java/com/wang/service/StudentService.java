package com.wang.service;

import com.wang.entity.Session;
import com.wang.entity.Student;

public interface StudentService extends BaseService<Student> {
    Student findAllByName(String username);
}
