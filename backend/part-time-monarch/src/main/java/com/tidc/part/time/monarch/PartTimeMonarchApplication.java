package com.tidc.part.time.monarch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
@MapperScan("com.tidc.part.time.monarch.mapper")
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
public class PartTimeMonarchApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartTimeMonarchApplication.class, args);
    }
}

