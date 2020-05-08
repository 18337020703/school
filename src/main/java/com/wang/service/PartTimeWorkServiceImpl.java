package com.wang.service;

import com.wang.dao.PartTimeWorkDao;
import com.wang.entity.Deal;
import com.wang.entity.PartTimeWork;
import com.wang.entity.Student;
import com.wang.entity.VoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PartTimeWorkServiceImpl implements PartTimeWorkService {
    @Autowired
    private PartTimeWorkDao partTimeWorkDao;
    @Override
    public void save(PartTimeWork partTimeWork) {
        partTimeWork.setWorkStatus(true);
        partTimeWork.setWorkRequest(false);
        Date date = new Date();
        partTimeWork.setReleaseDate(date);
        partTimeWorkDao.save(partTimeWork);
    }

    @Override
    public void update(PartTimeWork partTimeWork) {
        partTimeWorkDao.update(partTimeWork);
    }

    @Override
    public void delete(String id) {
        partTimeWorkDao.delete(id);
    }

    @Override
    public PartTimeWork findById(String id) {
        return partTimeWorkDao.findById(id);
    }

    @Override
    public List<PartTimeWork> findAll() {
        return partTimeWorkDao.findAll();
    }

    @Override
    public Long findTotalCounts() {
        return partTimeWorkDao.findTotalCounts();
    }

    @Override
    public List<PartTimeWork> findByPage(Integer start, Integer size) {
        return partTimeWorkDao.findByPage(start,size);
    }

    @Override
    public List<PartTimeWork> findByCondition(VoEntity voEntity) {
        return partTimeWorkDao.findByCondition(voEntity);
    }

    @Override
    public Integer findTotalCountsByCondition(PartTimeWork partTimeWork) {
        return partTimeWorkDao.findTotalCountsByCondition(partTimeWork);
    }

    @Override
    public Integer findTotalUser(VoEntity voEntity) {
        return partTimeWorkDao.findTotalUser(voEntity);
    }

    @Override
    public Integer findTotalDeal(VoEntity voEntity) {
        return partTimeWorkDao.findTotalDeal(voEntity);
    }

    @Override
    public void saveDeal(Deal deal) {
        String uuid = UUID.randomUUID().toString().replace("-","").toLowerCase();
        deal.setId(uuid);
        Date date = new Date();
        deal.setDate(date);
        partTimeWorkDao.saveDeal(deal);
    }

    @Override
    public List<Deal> findStudentWorkBySessionId(VoEntity voEntity) {
        return partTimeWorkDao.findStudentWorkBySessionId(voEntity);
    }

    @Override
    public List<Deal> findOverWork(Deal deal) {
        return partTimeWorkDao.findOverWork(deal);
    }

    @Override
    public PartTimeWork findWorkById(Integer id) {
        return partTimeWorkDao.findWorkById(id);
    }

    @Override
    public List<PartTimeWork> findPartTimeWorkByCondition(VoEntity voEntity) {
        return partTimeWorkDao.findPartTimeWorkByCondition(voEntity);
    }

    @Override
    public void editDeal(Deal deal) {
        partTimeWorkDao.editDeal(deal);
    }

    @Override
    public void editDealFinish(Deal deal) {
        partTimeWorkDao.editDealFinish(deal);
    }

    @Override
    public void deleteWork(Integer id) {
        partTimeWorkDao.deleteWork(id);
    }
}
