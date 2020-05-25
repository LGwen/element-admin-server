package com.lgwen.mybatisredismysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//EnableCaching 开启基于注解的缓存
@MapperScan("com.lgwen.mybatisredismysql.mapper")
@EnableCaching
public class MybatisRedisMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisRedisMysqlApplication.class, args);
    }

}
