package com.wang.controller;

import com.wang.entity.Merchants;
import com.wang.entity.Student;
import com.wang.entity.User;
import com.wang.entity.UserRole;
import com.wang.service.MerchantsService;
import com.wang.service.RegisterService;
import com.wang.service.StudentService;
import com.wang.utils.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("register")
@CrossOrigin(origins = "*")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StudentService studentService;
    @Autowired
    private MerchantsService merchantsService;

    @PostMapping
    public Student register(Student student, HttpServletRequest request){
//        if ( student.getPassword().equals(request.getParameter("password2"))){
//            registerService.save(student);
//        }else {
//            System.out.println("两次密码输入不一致");
//        }
//        System.out.println("注册成功"+student);
//        System.out.println("otherData"+request.getParameter("password2"));
//        System.out.println("otherData"+request.getParameter("Nickname"));
//        System.out.println("otherData"+request.getParameter("college"));
//        System.out.println("otherData"+request.getParameter("sex"));
//        System.out.println("otherData"+request.getParameter("phonenumber"));
        return student;
    }

    /**
     * 发送手机验证码
     * @param phone
     * @return
     */
    @PostMapping("/sendMessageCode")
    public Map sendMessageCode(@RequestBody String phone){
        Map<String, Object> map = new HashMap<>();
        Student studentName = studentService.findAllByName(phone);
        Merchants merchantsName = merchantsService.findAllByUsername(phone);
        String frist = (String) phone.subSequence(0,1);
        String second = (String) phone.subSequence(1,2);
        System.out.println(frist);
        if (phone.length()!=11 || !frist.equals("1") || second.equals("2")){
            map.put("msg","手机号码格式错误");
            return map;
        }else if (studentName !=null | merchantsName != null){
            //System.out.println("账号已经被注册");
            map.put("msg","手机号码已经被注册");
            return map;
        }else {
            System.out.println("phone"+phone);
            Random random = new Random();
            Integer code = random.nextInt(999999);
            System.out.println(code);
            ValueOperations<String, String> StringValueOperations = stringRedisTemplate.opsForValue();
           StringValueOperations.set(phone,Integer.toString(code),100, TimeUnit.SECONDS);
            //StringValueOperations.set(phone,Integer.toString(code),3000);
            //验证码发送
            Code.code(phone,code);
            map.put("msg","发送成功");
            return map;
        }
    }

    /**
     * 接收验证码，并且对比，成功则注册成功
     * @param user
     * @return
     */
    @PostMapping("receiveMessageCode")
    public Map receive(User user){
        String radio = "1"; //代表商家
        String radio1 = "0";//代表学生
        System.out.println(user);
        Map<Object, Object> map = new HashMap<>();
        ValueOperations<String,String> StringValueOperations = stringRedisTemplate.opsForValue();
            String RedisCode = StringValueOperations.get(user.getPhone());
            System.out.println("RedisCode"+RedisCode);
            //设置一个UUID
            String id = UUID.randomUUID().toString().replace("-","").toLowerCase();
            System.out.println(id);
            //密码加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePassword = encoder.encode(user.getPassword());
        int length = user.getPassword().length();
        if (length<6 || length>12){
                map.put("msg","密码必须为*6-12*位");
                return map;
            }else if (RedisCode.equals(user.getCode())){
                System.out.println("比对成功");
                /**
                 * 如果客户选择注册成为学生
                 */
                if (radio1.equals(user.getSex())){
                    Student student = new Student();
                    student.setUsername(user.getPhone());
                    student.setId(id);
                    student.setPassword(encodePassword);
                    registerService.save(student);
                    UserRole userRole = new UserRole();
                    userRole.setUid(id);
                    userRole.setRid(2);
                    registerService.setStudentRole(userRole);
                }
                /**
                 * 如果客户选择注册成为商家
                 */
                else if (radio.equals(user.getSex())){
                    System.out.println("zhuceMerchants");
                    Merchants merchants = new Merchants();
                    merchants.setUsername(user.getPhone());
                    merchants.setId(id);
                    merchants.setPhone(user.getPhone());
                    merchants.setPassword(encodePassword);
                    registerService.saveMerchants(merchants);
                    System.out.println("zhuceMerchants---");
                    UserRole userRole = new UserRole();
                    userRole.setUid(id);
                    userRole.setRid(3);
                    registerService.setMerchantsRole(userRole);
                }
                    map.put("msg","注册成功");
                return map;
            }else {
                map.put("msg","验证码不正确");
                return map;
            }
        }


    }

