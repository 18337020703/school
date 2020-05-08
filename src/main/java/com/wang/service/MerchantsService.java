package com.wang.service;

import com.wang.entity.Merchants;

public interface MerchantsService extends BaseService<Merchants> {
    Merchants findAllByUsername(String username);
}
