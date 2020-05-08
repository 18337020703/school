package com.wang.service;

import com.wang.dao.UserMapper;
import com.wang.entity.Student;
import com.wang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurity implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    /**
     * 根据前端传入的用户名去数据库查找其权限并附上
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // System.out.println("###########################################第一步：根据登录名查询###############################################################################################################");
        if (username == null){
            throw new UsernameNotFoundException("账户不存在");
        }
        User user = userMapper.loadUserByUsername(username);
        if ( user == null){
            System.out.println("user==null");
            User merchants = userMapper.loadMerchantsByUsername(username);
            merchants.setRoles(userMapper.getUserRolesByUid(merchants.getId()));
            System.out.println("当前登录的用户信息为merchants——————>"+merchants);
            return merchants;
        }else {
            System.out.println("user!=null");
            user.setRoles(userMapper.getUserRolesByUid(user.getId()));
            System.out.println("当前登录的用户信息为user——————>"+user);
            return user;
        }

        //System.out.println("给用户装配从数据库获取的权限——————>"+user.getRoles());
    }
}
