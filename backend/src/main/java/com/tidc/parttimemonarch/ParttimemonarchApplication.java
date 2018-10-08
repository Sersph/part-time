package com.tidc.parttimemonarch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.tidc.parttimemonarch.dao")
@EnableCaching
@EnableScheduling
public class ParttimemonarchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParttimemonarchApplication.class, args);
    }

}
