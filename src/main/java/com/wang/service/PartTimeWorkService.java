package com.wang.service;

import com.wang.entity.Deal;
import com.wang.entity.PartTimeWork;
import com.wang.entity.VoEntity;

import java.util.List;

public interface PartTimeWorkService extends BaseService<PartTimeWork> {
    List<PartTimeWork> findByCondition(VoEntity voEntity);
    Integer findTotalCountsByCondition(PartTimeWork partTimeWork);
    Integer findTotalUser(VoEntity voEntity);
    Integer findTotalDeal(VoEntity voEntity);
    void saveDeal(Deal deal);
    List<Deal> findStudentWorkBySessionId(VoEntity voEntity);
    List<Deal> findOverWork(Deal deal);
    PartTimeWork findWorkById(Integer id);
    List<PartTimeWork> findPartTimeWorkByCondition(VoEntity voEntity);
    void editDeal(Deal deal);
    void editDealFinish(Deal deal);
    void deleteWork(Integer id);
}
