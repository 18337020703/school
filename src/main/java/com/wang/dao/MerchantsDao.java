package com.wang.dao;

import com.wang.entity.Merchants;
import org.springframework.stereotype.Component;

@Component
public interface MerchantsDao extends BaseDao<Merchants> {
    Merchants findAllByUsername(String username);
}
