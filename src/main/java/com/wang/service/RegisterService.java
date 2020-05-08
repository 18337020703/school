package com.wang.service;

import com.wang.entity.Merchants;
import com.wang.entity.Student;
import com.wang.entity.User;
import com.wang.entity.UserRole;

public interface RegisterService extends BaseService<Student> {
    void saveMerchants(Merchants merchants);
    void setStudentRole(UserRole userRole);
    void setMerchantsRole(UserRole userRole);
}
