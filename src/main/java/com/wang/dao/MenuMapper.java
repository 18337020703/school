package com.wang.dao;

import com.wang.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MenuMapper {
    List<Menu> getAllMenus();
}
