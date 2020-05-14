package com.framework.hanason.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sorata
 */
@SpringBootApplication
@EnableAsync
@MapperScan(value= "com.framework.hanason.app.dao")
public class HanasonAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanasonAppApplication.class, args);
    }

}
