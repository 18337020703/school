package com.wang.controller;

import com.wang.entity.Message;
import com.wang.entity.User;
import com.wang.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("message")
@CrossOrigin(origins = "*")
public class TestController {
//    @Autowired
//    private RegisterService registerService;
    /**
     * get请求
     * @param response
     * @return
     */
    @GetMapping
    public List<Message> getMessage(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Message message1 = new Message(1,"wangxiangbin","jiayoujiayou","哈哈哈哈哈哈哈哈");
        Message message2 = new Message(2,"wangxiangbin","jiayoujiayou","hehehehehehehehe");
        List<Message> data = new ArrayList<>();
        data.add(message1);
        data.add(message2);
        System.out.println(data);
        return data;
    }

    /**
     * post请求
     * @param user
     * @return
     */
    @PostMapping
    public User postMessage(User user){
        //registerService.save();
        System.out.println("message"+user);
        return user;
    }
    @PutMapping("put")
    public Message putMessage(Message message,HttpServletResponse response){
        System.out.println("修改"+message);
        return message;
    }
    @DeleteMapping("{id}")
    public void del(@PathVariable Long id,HttpServletResponse response){
        System.out.println("删除id为"+id+"的员工");
    }
}
