package com.tidc.parttimemonarch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tidc.parttimemonarch.dao")
public class ParttimemonarchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParttimemonarchApplication.class, args);
    }
}
