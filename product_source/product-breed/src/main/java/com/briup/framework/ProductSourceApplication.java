package com.briup.framework;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: product_source
 * @description: 农产品溯源启动类
 * @author: pgc
 * @create:2022-03-21
 **/
@SpringBootApplication
@MapperScan("com.briup.*.mapper")
@EnableScheduling
public class ProductSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductSourceApplication.class, args);
    }
}
