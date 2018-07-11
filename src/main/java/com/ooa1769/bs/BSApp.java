package com.ooa1769.bs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BSApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BSApp.class)
                .properties("spring.config.location=classpath:kakao.properties")
                .run(args);
    }
}
