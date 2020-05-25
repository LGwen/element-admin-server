package com.lgwen.mybatisredismysql;

import com.lgwen.mybatisredismysql.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisRedisMysqlApplicationTests {
    @Autowired
    UserController userController;
    @Test
    void contextLoads() {
        userController.findAll();
    }

}
