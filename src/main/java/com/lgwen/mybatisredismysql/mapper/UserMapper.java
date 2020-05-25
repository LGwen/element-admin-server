package com.lgwen.mybatisredismysql.mapper;

import com.lgwen.mybatisredismysql.beans.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import lombok.Delegate;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from users where id=#{id}")
    public User getUserById(int id);

    @Select("select * from users where username=#{name}")
    public User findByUsername(String name);

    @Select("select * from users")
    public List<User> findAllUser();

    @Select("insert into users values(null,#{username},#{password})")
    public void addUser(String username, String password);

    @Update("update users set username=#{username},password=#{password} where id=#{id}")
    public void updateUserById(int id, String username, String password);

    @Delete("delete from users where id=#{id}")
    public void deleUserById(int id);
}
