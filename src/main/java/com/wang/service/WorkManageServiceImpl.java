package com.wang.service;

import com.wang.dao.WorkManageDao;
import com.wang.entity.WorkManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkManageServiceImpl implements WorkManageService {
    @Autowired
    private WorkManageDao workManageDao;
    @Override
    public void save(WorkManage workManage) {
        workManageDao.save(workManage);
    }

    @Override
    public void update(WorkManage workManage) {
        workManageDao.update(workManage);
    }

    @Override
    public void delete(String id) {
        workManageDao.delete(id);
    }

    @Override
    public WorkManage findById(String id) {
        return workManageDao.findById(id);
    }

    @Override
    public List<WorkManage> findAll() {
        return workManageDao.findAll();
    }

    @Override
    public Long findTotalCounts() {
        return workManageDao.findTotalCounts();
    }

    @Override
    public List<WorkManage> findByPage(Integer start, Integer size) {
        return workManageDao.findByPage(start, size);
    }
}
