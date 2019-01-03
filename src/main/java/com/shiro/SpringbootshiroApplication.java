package com.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@MapperScan("com.shiro.mapper")
@SpringBootApplication
public class SpringbootshiroApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(SpringbootshiroApplication.class);
        builder.bannerMode(Banner.Mode.OFF).run(args);
    }

}

