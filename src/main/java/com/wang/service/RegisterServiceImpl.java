package com.wang.service;

import com.wang.dao.RegisterDao;
import com.wang.entity.Merchants;
import com.wang.entity.Student;
import com.wang.entity.User;
import com.wang.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;
    @Override
    public void save(Student student) {
        student.setNickname("默认用户名");
        registerDao.save(student);
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Student findById(String id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Long findTotalCounts() {
        return null;
    }

    @Override
    public List<Student> findByPage(Integer start, Integer size) {
        return null;
    }


    @Override
    public void saveMerchants(Merchants merchants) {
        registerDao.saveMerchants(merchants);
    }

    @Override
    public void setStudentRole(UserRole userRole) {
        registerDao.setStudentRole(userRole);
    }

    @Override
    public void setMerchantsRole(UserRole userRole) {
        registerDao.setMerchantsRole(userRole);
    }
}
