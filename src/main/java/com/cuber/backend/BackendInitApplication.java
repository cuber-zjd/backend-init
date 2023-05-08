package com.cuber.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cuber.backend.system.mapper")
public class BackendInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendInitApplication.class, args);
    }

}
