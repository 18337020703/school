package com.wang.dao;

import com.wang.entity.Role;
import com.wang.entity.Student;
import com.wang.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserMapper {
    User loadUserByUsername(String username);
    User loadMerchantsByUsername(String username);
    List<Role> getUserRolesByUid(String id);
}
