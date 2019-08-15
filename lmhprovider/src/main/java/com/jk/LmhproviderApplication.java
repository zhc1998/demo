package com.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jk.mapper")
public class LmhproviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmhproviderApplication.class, args);
    }

}
