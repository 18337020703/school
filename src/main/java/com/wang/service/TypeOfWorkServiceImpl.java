package com.wang.service;

import com.wang.dao.TypeOfWorkDao;
import com.wang.entity.TypeOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeOfWorkServiceImpl implements TypeOfWorkService {
    @Autowired
    private TypeOfWorkDao typeOfWorkDao;
    @Override
    public void save(TypeOfWork typeOfWork) {
        typeOfWorkDao.save(typeOfWork);
    }

    @Override
    public void update(TypeOfWork typeOfWork) {
typeOfWorkDao.update(typeOfWork);
    }

    @Override
    public void delete(String id) {
typeOfWorkDao.delete(id);
    }

    @Override
    public TypeOfWork findById(String id) {
        return typeOfWorkDao.findById(id);
    }

    @Override
    public List<TypeOfWork> findAll() {
        return typeOfWorkDao.findAll();
    }

    @Override
    public Long findTotalCounts() {
        return typeOfWorkDao.findTotalCounts();
    }

    @Override
    public List<TypeOfWork> findByPage(Integer start, Integer size) {
        return typeOfWorkDao.findByPage(start,size);
    }
}
