package com.wang.controller;

import com.wang.entity.Merchants;
import com.wang.entity.Session;
import com.wang.entity.Student;
import com.wang.entity.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("session")
@CrossOrigin(origins = "*")
public class SessionController {
    /**
     * 从reids中获取当前用户的session
     * @param httpServletRequest
     * @return
     */
    @GetMapping
    public Map getSession(HttpServletRequest httpServletRequest){
       // System.out.println("进来啦");
        Map<Object, Object> map = new HashMap<>();
        /**
         * 首先查查有没有学生用户的session，没有就去查商家的session
         */
            Student redisUser = (Student) httpServletRequest.getSession().getAttribute("student");
            //System.out.println("id:"+httpServletRequest.getSession().getId());
            //System.out.println("sessionControllerRedisUSer---"+redisUser);
            map.put("session",redisUser);
            return map;
    }
    @GetMapping("merchants")
    public Map getMerchantSession(HttpServletRequest httpServletRequest){
            Map<Object, Object> map = new HashMap<>();
            Merchants redisUser1 = (Merchants) httpServletRequest.getSession().getAttribute("merchants");
            //System.out.println("id:"+httpServletRequest.getSession().getId());
            //System.out.println("sessionControllerRedisUSer---"+redisUser1);
            map.put("session",redisUser1);
            return map;
    }
}
