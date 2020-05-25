package com.lgwen.mybatisredismysql.service;

import com.lgwen.mybatisredismysql.beans.User;
import com.lgwen.mybatisredismysql.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Cacheable(cacheNames = "user",key = "#id")
    public User getUser(int id){
        System.out.println("查询了"+id+"号用户");
        return userMapper.getUserById(id);
    }
//    @Cacheable(cacheNames = "allUser")
    public List<User> findAll(){
        return (List<User>) userMapper.findAllUser();
    }

    public User findUserByName(User user){
        System.out.println("查询了"+user.getUsername()+"用户");
        return userMapper.findByUsername(user.getUsername());
    }

    public void addUser(User user) {
        userMapper.addUser(user.getUsername(), user.getPassword());
    }
    @Cacheable(cacheNames = "user",key = "#user.id")
    public void updateUserById(User user) {
        System.out.println("updateuser:" + user);
        userMapper.updateUserById(user.getId(), user.getUsername(), user.getPassword());
    }
    
    // 删除redis 同时删除mysql
    @CacheEvict(cacheNames = "user",key = "#id")
    public void deleteUserById(int id) {
        System.out.println("deleUserById:" + id);
        userMapper.deleUserById(id);
    }
}
