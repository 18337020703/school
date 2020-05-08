package com.wang.controller;

import com.wang.utils.HttpUtil;
import com.wang.entity.Merchants;
import com.wang.entity.Student;
import com.wang.service.MerchantsService;
import com.wang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private MerchantsService merchantsService;

    /**
     * 查询所有的学生
     * @return
     */
    @GetMapping("student")
    public Map getStudent(){
        //System.out.println("chaxunsuoyouxuesheng");
        List<Student> list = studentService.findAll();
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("code",0);
        return map;
    }

    /**
     * 根据ID查询一个学生
     * @param id
     * @return
     */
    @GetMapping("student/{id}")
    public Map getStudentById(@PathVariable("id")String id){
        Student list = studentService.findById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",list);
        return map;
    }

    /**
     * 查询所有商家
     * @return
     */
    @GetMapping("merchants")
    public Map getMerchants(){
        List<Merchants> list = merchantsService.findAll();
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("code",0);
        return map;
    }

    /**
     * 根据ID查询商家
     * @param id
     * @return
     */
    @GetMapping("merchants/{id}")
    public Map getMerchantsById(@PathVariable("id") String id){
        Merchants list = merchantsService.findById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",list);
        return map;
    }

    /**
     * 修改学生的个人资料
     * @param student
     * @return
     */
    @PostMapping("student")
    public String editStudent(Student student){
        //System.out.println("传过来的个人资料"+student);
        studentService.update(student);
        return "修改成功";
    }
    @PostMapping("setLocked")
    public Map setLocked(@RequestBody Student student){
        Map<String,Object> map = new HashMap<>();
        //System.out.println("student----"+student);
        studentService.save(student);
        if (student.getLocked()){
            map.put("msg", "锁定用户");
        }else {
            map.put("msg","解除锁定");
        }
        return map;
    }
    /**
     * 修改商家的资料
     * @param merchants
     * @return
     */
    @PostMapping("merchants")
    public Map editMerchants( Merchants merchants){
        //System.out.println("传过来的个人资料"+merchants);
        merchantsService.update(merchants);
        Map<String,Object> map = new HashMap<>();
        map.put("res","修改成功");
        return map;
    }
    /**
     * 修改商家的资料
     * @param merchants
     * @return
     */
    @PostMapping("locked")
    public Map setMerchantsLocked(@RequestBody Merchants merchants){
        //System.out.println("传过来的个人资料"+merchants);
        merchantsService.update(merchants);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","修改成功");
        return map;
    }

    /**
     * 上传用户头像并做压缩处理
     * 踩坑：上传图片不要写本地路径，要写网络路径
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
        @PostMapping("upload")
        public Map userupimg(@RequestParam(value = "file")MultipartFile file, HttpServletRequest request) throws IOException {
//           String path = request.getSession().getServletContext().getRealPath("/upload");
            HashMap<String,Object> map = new HashMap<>();
//            String filename = UUID.randomUUID().toString().replaceAll("-", "");
//            Student student = (Student) request.getSession().getAttribute("student");
//            String ext = FilenameUtils.getExtension(file.getOriginalFilename());//得到文件名的后缀名
//            String filenames = filename + "." + ext;
//          //  String pathname = "D:\\wang\\shool_demo\\src\\main\\resources\\static\\upload\\"+
//            String pathname = path+filenames;
//            //String pathname = "D:\\wang\\shool_demo\\src\\main\\resources\\static\\upload\\"+filenames;
//            student.setPath(path+filenames);
//            System.out.println(student.getPath());
//            studentService.save(student);
//            if("png".equals(ext)){
//                String pathname1 = "D:\\wang\\shool_demo\\src\\main\\resources\\static\\upload\\"+filename;
//
//                //质量处理 为.scale(1f) 默认为0.75
//                //裁剪处理 .size(400,400).keepAspectRatio(false)
//                //转换格式 .outputFormat("jpg")
//                Thumbnails.of(file.getInputStream()).outputFormat("jpg").scale(0.2f).outputQuality(0.2f).toFile(new File(pathname1));
//                map.put("url","../upload/"+filename+"jpg");
//            }else {
//                try {
//                   // Thumbnails.of(file.getInputStream()).size(400,400).keepAspectRatio(false).outputQuality(0.2f).toFile(new File(pathname));
//                    file.transferTo(new File(pathname));
//                } catch (IOException e) {
//                    try {
//                        file.transferTo(new File(pathname));//往本地存入该路径的文件
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//                map.put("url","../upload/"+filenames);
//            }
//              map.put("msg", "上传成功");
//            return map;
            String url = HttpUtil.getHttpUrl(file, request, request.getSession(), "/upload");
            Student student = (Student) request.getSession().getAttribute("student");
            student.setPath(url);
            studentService.save(student);
            map.put("url",url);
            return map;
        }
}
