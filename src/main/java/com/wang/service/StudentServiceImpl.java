package com.wang.service;


import com.wang.dao.StudentDao;
import com.wang.entity.Session;
import com.wang.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public void save(Student student) {
studentDao.save(student);
    }

    @Override
    public void update(Student student) {
        System.out.println("service里面的——————>"+student);
        studentDao.update(student);
    }

    @Override
    public void delete(String id) {
studentDao.delete(id);
    }

    @Override
    public Student findById(String id) {
        return studentDao.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Long findTotalCounts() {
        return studentDao.findTotalCounts();
    }

    @Override
    public List<Student> findByPage(Integer start, Integer size) {
        return studentDao.findByPage(start,size);
    }

    @Override
    public Student findAllByName(String username) {
        return studentDao.findAllByName(username);
    }
}
