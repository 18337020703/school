package com.wang.utils;

import com.wang.entity.Deal;
import com.wang.entity.PartTimeWork;
import com.wang.entity.Student;
import com.wang.service.PartTimeWorkService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工具类
 */
@Component
public class Utils {
    /**
     * 为遍历出来的数据list每个index加上属性
     * @param list
     * @param partTimeWorkService
     * @param httpServletRequest
     * @return
     */
    public List<PartTimeWork> setStatus(List<PartTimeWork> list, PartTimeWorkService partTimeWorkService, HttpServletRequest httpServletRequest){
        Student student = (Student) httpServletRequest.getSession().getAttribute("student");
        if (student!=null){
            for (int i=0;i<list.size();i++){
                //System.out.println("进来第"+i+"次");
                Deal deal = new Deal();
                deal.setSid(student.getId());
                Integer id = list.get(i).getId();
                deal.setPid(id);
                //System.out.println(deal);
                List<Deal> studentWork= partTimeWorkService.findOverWork(deal);
                if (studentWork.size()!=0){
                    list.get(i).setThisStatus(true);
                }
            }
        }
        return list;
    }
}
