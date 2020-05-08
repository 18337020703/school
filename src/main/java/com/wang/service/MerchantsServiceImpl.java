package com.wang.service;

import com.wang.dao.MerchantsDao;
import com.wang.entity.Merchants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MerchantsServiceImpl implements MerchantsService {
    @Autowired
    private MerchantsDao merchantsDao;
    @Override
    public void save(Merchants merchants) {
    merchantsDao.save(merchants);
    }

    @Override
    public void update(Merchants merchants) {
merchantsDao.update(merchants);
    }

    @Override
    public void delete(String id) {
merchantsDao.delete(id);
    }

    @Override
    public Merchants findById(String id) {
        return merchantsDao.findById(id);
    }

    @Override
    public List<Merchants> findAll() {
        return merchantsDao.findAll();
    }

    @Override
    public Long findTotalCounts() {
        return merchantsDao.findTotalCounts();
    }

    @Override
    public List<Merchants> findByPage(Integer start, Integer size) {
        return merchantsDao.findByPage(start,size);
    }

    @Override
    public Merchants findAllByUsername(String username) {
        return merchantsDao.findAllByUsername(username);
    }
}
