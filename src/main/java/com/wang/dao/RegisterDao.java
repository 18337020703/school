package com.wang.dao;

import com.wang.entity.Merchants;
import com.wang.entity.Student;
import com.wang.entity.User;
import com.wang.entity.UserRole;
import org.springframework.stereotype.Component;
@Component
public interface RegisterDao extends BaseDao<Student> {
    void saveMerchants(Merchants merchants);
    void setStudentRole(UserRole userRole);
    void setMerchantsRole(UserRole userRole);
}
