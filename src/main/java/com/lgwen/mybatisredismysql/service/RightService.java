package com.lgwen.mybatisredismysql.service;

import com.lgwen.mybatisredismysql.beans.Right;
import com.lgwen.mybatisredismysql.beans.User;
import com.lgwen.mybatisredismysql.mapper.RightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RightService {
    @Autowired
    RightMapper rightMapper;

    @Cacheable(cacheNames = "right",key = "#id")
    public Right getUserRight(int id){
        System.out.println("查询了"+id+"号用户的权限");
        return rightMapper.getUserRightByUserId(id);
    }
}
