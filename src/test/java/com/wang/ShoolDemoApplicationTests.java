package com.wang;

import com.wang.entity.Student;
import com.wang.entity.User;
import com.wang.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest(classes = ShoolDemoApplication.class)
@RunWith(SpringRunner.class)
public class ShoolDemoApplicationTests {
    @Autowired
    private RegisterService registerService;
    @Test
   public void find(Student student) {
        registerService.save(student);
    }
    @Test
    public void uuid(){
        String id = UUID.randomUUID().toString().replace("-","").toLowerCase();
        System.out.println("if");
    }
}
