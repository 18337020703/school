package com.wang.controller;


import com.wang.utils.Utils;
import com.wang.entity.*;
import com.wang.service.MerchantsService;
import com.wang.service.PartTimeWorkService;
import com.wang.service.StudentService;
import com.wang.service.TypeOfWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("parttimework")
@CrossOrigin(origins = "*")
public class PartTimeWorkController {
    @Autowired
    private PartTimeWorkService partTimeWorkService;
    @Autowired
    private TypeOfWorkService typeOfWorkService;
    @Autowired
    private MerchantsService merchantsService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private Utils utils;
    /**
     * 添加兼职类 商家
     * @param partTimeWork
     * @return
     */
    @PostMapping
    public Map addPartTimeWork(PartTimeWork partTimeWork,HttpServletRequest request){
        //System.out.println(partTimeWork);
        Merchants merchants = (Merchants) request.getSession().getAttribute("merchants");
        //System.out.println("parttimeworkUser"+merchants);
       partTimeWork.setCompanyId(merchants.getId());
        partTimeWorkService.save(partTimeWork);
        Map<String,Object> map = new HashMap<>();

        return map;
    }

    /**
     * 修改当前工作的完成情况
     * @param partTimeWork
     * @return
     */
    @PostMapping("edit")
    public Map editPartTimeWork(PartTimeWork partTimeWork){
       // System.out.println(partTimeWork);
        partTimeWorkService.update(partTimeWork);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","修改成功");
        return map;
    }


    /**
     * 添加交易信息，学生报名
     * @param id
     * @param request
     * @return
     */
    @PostMapping("deal")
    public Map saveDeal(@RequestBody Integer id,HttpServletRequest request){
       // System.out.println("deal"+id);
        Map<String,Object> map = new HashMap<>();
        Student student = (Student) request.getSession().getAttribute("student");
        if (student!=null){
            Deal deal = new Deal();
            deal.setPid(id);
            deal.setSid(student.getId());
            partTimeWorkService.saveDeal(deal);
            map.put("res","报名成功");
            return map;
        }else {
            map.put("res","请您先登录");
            return map;
        }
    }

    /**
     * 同意学生的兼职
     * @param
     * @return
     */
    @PostMapping("editDeal")
    public Map editDeal(@RequestBody Deal deal){
        //System.out.println(deal);
        //System.out.println("idd"+deal.toString());
        //System.out.println("pid"+pid);
       partTimeWorkService.editDeal(deal);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","操作成功");
        return map;
    }

    /**
     * 下架兼职
     * @param deal
     * @return
     */
    @PostMapping("editDealFinish")
    public Map editDealFinish(@RequestBody Deal deal){
       // System.out.println(deal);
        //System.out.println("idd"+deal.toString());
        //System.out.println("pid"+pid);
        partTimeWorkService.editDealFinish(deal);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","操作成功");
        return map;
    }

    /**
     * 分页查询兼职
     * @param start
     * @param size
     * @return
     */
    @GetMapping("{start}/{size}")
    public Map getPartTimeWork(@PathVariable("start") Integer start, @PathVariable("size") Integer size, HttpServletRequest httpServletRequest){
        //System.out.println("fenye"+start+size);
        Integer start1 = (start-1)*size;
        List<PartTimeWork> list = partTimeWorkService.findByPage(start1, size);
        utils.setStatus(list,partTimeWorkService,httpServletRequest);
        Map<String,Object> map = new HashMap();
        map.put("data",list);
        return map;
    }

    /**
     * 根据条件分页查找数据
     * @param
     * @param start
     * @param size
     * @return
     */
    @GetMapping("condition/{start}/{size}")
    public Map getPartTimeWorkByCondition(VoEntity voEntity,@PathVariable("start") Integer start,@PathVariable("size") Integer size,HttpServletRequest httpServletRequest){
       // System.out.println("条件查找"+start+","+size);
       // System.out.println(partTimeWork.getTypeOfWork());
        //String condition = partTimeWork.getTypeOfWork();
        Integer start1 = (start-1)*size;
        voEntity.setStart(start1);
        voEntity.setSize(size);
        List<PartTimeWork> list = partTimeWorkService.findByCondition(voEntity);
       // System.out.println(list);
        utils.setStatus(list,partTimeWorkService,httpServletRequest);
        Map<String,Object> map = new HashMap();
        map.put("data",list);
        map.put("code",0);
        return map;
    }
    /**
     * 根据条件查询商家发布的动态
     * @param
     * @return
     */
    @GetMapping("merchantsIssue")
    public Map<String,Object> findMerchantsIssueByCondition(VoEntity voEntity,HttpServletRequest httpServletRequest){
       // System.out.println("根据条件查询商家发布的动态");
        Map<String,Object> map = new HashMap<>();
        Merchants merchants = (Merchants) httpServletRequest.getSession().getAttribute("merchants");
        voEntity.setCompanyId(merchants.getId());
        Integer start =(voEntity.getStart()-1)*voEntity.getSize();
        voEntity.setStart(start);
        List<PartTimeWork> list = partTimeWorkService.findPartTimeWorkByCondition(voEntity);
        utils.setStatus(list,partTimeWorkService,httpServletRequest);
        map.put("data",list);
        return map;
    }

    /**
     * 查看当前id为{}的工作的所有报名学生
     * @param id
     * @return
     */
    @GetMapping("studentList/{id}")
    public Map findWorkWithStudent(@PathVariable("id") Integer id){
        //System.out.println("id"+id);
        List<StudentList> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
       // Deal deal = new Deal();
        VoEntity voEntity = new VoEntity();
        //deal.setPid(id);
        voEntity.setPartTimeWorkId(id);
       // System.out.println("deal"+deal);
        List<Deal> studentWork = partTimeWorkService.findStudentWorkBySessionId(voEntity);
        //System.out.println("studentWork"+studentWork);
        for (int i = 0;i< studentWork.size();i++){
            Student student = studentService.findById(studentWork.get(i).getSid());
            StudentList studentList = new StudentList(student.getId(), student.getNickname(), student.getSex(), student.getPhone(),
                    student.getBir(), student.getUniversity(), student.getDepartment(),
                       student.getProfession(), studentWork.get(i).getStatus(),
                          studentWork.get(i).getFinish(), studentWork.get(i).getDay());
            //System.out.println("studentList"+studentList);
            list.add(studentList);
        }
        System.out.println("list"+list);
            map.put("code",0);
            map.put("data",list);
        return map;
    }
    /**
     * 查出所有的工作类型
     * @return
     */
    @GetMapping("typeofwork")
    public Map getTypeOfWork(){
        List<TypeOfWork> list = typeOfWorkService.findAll();
        //System.out.println("list"+list);
        Map<String,Object> map = new HashMap();
        map.put("data",list);
        return map;
    }

    /**
     * 查询数据的总条数
     * @return
     */
    @GetMapping("totalCounts")
    public Integer getTotalCounts(VoEntity voEntity,HttpServletRequest httpServletRequest){
        Merchants merchants = (Merchants) httpServletRequest.getSession().getAttribute("merchants");
        voEntity.setCompanyId(merchants.getId());
        Integer totalCounts = partTimeWorkService.findTotalUser(voEntity);
        return totalCounts;
    }

    /**
     * 查询某个条件下的总数据数
     * 踩坑，mybatis里不要打注释，否则很蛋疼
     * @param partTimeWork
     * @return
     */
    @GetMapping("totalCountsByCondition")
    public Integer getTotalCountsByCondition(PartTimeWork partTimeWork){
       // System.out.println("条件总数");
        Integer totalCountsByCondition = partTimeWorkService.findTotalCountsByCondition(partTimeWork);
        //System.out.println("总条数为"+totalCountsByCondition);
        return totalCountsByCondition;
    }

    /**
     * 查询当前session下的工作记录
     * @param voEntity
     * @param httpServletRequest
     * @return
     */
    @GetMapping("findDealTotal")
    public Integer findDealTotal(VoEntity voEntity,HttpServletRequest httpServletRequest){
        Student student = (Student) httpServletRequest.getSession().getAttribute("student");
        voEntity.setStudentId(student.getId());
        Integer totalDeal = partTimeWorkService.findTotalDeal(voEntity);
        return totalDeal;
    }
    /**
     * 学生登录后看到自己的兼职记录
     * @param httpServletRequest
     * @return
     */
    @GetMapping("studentWork/{start}/{size}")
    public Map<String,Object> findStudentWork(VoEntity voEntity,@PathVariable("start") Integer start,@PathVariable("size") Integer size,HttpServletRequest httpServletRequest){
        //System.out.println("哈哈哈哈哈哈哈哈");
       Double salary = 0.00;
       int times = 0;
        Map<String,Object> map = new HashMap<>();
        List<StudentWorkTable> list = new ArrayList<>();
        Student student = (Student) httpServletRequest.getSession().getAttribute("student");
       // System.out.println("学生登录后看到自己的兼职记录"+student);
        voEntity.setStart(start);voEntity.setSize(size);
        voEntity.setStudentId(student.getId());
        //System.out.println("start"+voEntity.getStart()+"size"+voEntity.getSize());
        Integer start1 =(voEntity.getStart()-1)*voEntity.getSize();
        voEntity.setStart(start1);
       // System.out.println("start"+voEntity.getStart());
        List<Deal> deal = partTimeWorkService.findStudentWorkBySessionId(voEntity);
        //System.out.println(deal);
        for (int i=0; i<deal.size();i++){
            Integer pid = deal.get(i).getPid();
            System.out.println("pid"+pid);
            PartTimeWork work = partTimeWorkService.findWorkById(pid);
            System.out.println("work"+work.getCompanyId());
            System.out.println("finish"+deal.get(i).getFinish());
            Merchants merchants = merchantsService.findById(work.getCompanyId());
            System.out.println("mer+"+merchants);
            StudentWorkTable studentWorkTable = new StudentWorkTable(work.getTitle(), work.getContent(), merchants.getId(), work.getSalary(), merchants.getCompanyName(), work.getWorkDate(), deal.get(i).getDay(), work.getWorkAddress(), work.getTypeOfWork(),deal.get(i).getFinish(),deal.get(i).getStatus());
            System.out.println("信息为"+studentWorkTable);
            list.add(studentWorkTable);
            if (deal.get(i).getFinish()){
                System.out.println("第"+i+"次");
                salary+= work.getSalary();
                times++;
            }
           // System.out.println("sum"+salary);
        }
        //System.out.println("集合为"+list);
        map.put("data",list);
        map.put("sum",salary);
        map.put("times",times);
        return map;
    }

    /**
     * 获取所有的交易记录
     * @return
     */
    @GetMapping("findAllDeal")
    public Map getAllDeal(){
        Map<String,Object> map = new HashMap<>();
        VoEntity voEntity = new VoEntity();
        List<Deal> list = partTimeWorkService.findStudentWorkBySessionId(voEntity);
        map.put("data",list);
        map.put("code",0);
        return map;
    }
    /**
     * 根据id删除一个兼职
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Map delPartTimeWork(@PathVariable("id") Integer id){
        //System.out.println("del"+id);
        partTimeWorkService.deleteWork(id);
        Map<String,Object> map = new HashMap();
        map.put("msg","删除成功");
        return map;
    }

    /**
     * 修改兼职的全部信息
     * @param partTimeWork
     * @return
     */
    @PutMapping
    public Map putPartTimeWork(PartTimeWork partTimeWork){
        partTimeWorkService.update(partTimeWork);
        Map<String,Object> map = new HashMap();
        map.put("msg","删除成功");
        return map;
    }

    /**
     * 修改个别信息
     * @return
     */
    @PatchMapping("status")
    public Map PatchPartTimeWork(@RequestBody PartTimeWork partTimeWork){
        //System.out.println("patch"+partTimeWork);
        partTimeWorkService.update(partTimeWork);
        Map<String,Object> map = new HashMap();
        if(partTimeWork.getWorkRequest()){
            map.put("msg","审核通过");
        }else {
            map.put("msg", "强制下架");
        }
        return map;
    }
}
