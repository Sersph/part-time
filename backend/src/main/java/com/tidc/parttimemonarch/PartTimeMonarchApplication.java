package com.tidc.parttimemonarch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("com.tidc.parttimemonarch.dao")
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7200)
public class PartTimeMonarchApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartTimeMonarchApplication.class, args);
    }

}
