package com.ooa1769.bs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BSApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BSApp.class)
                .properties("spring.config.location=classpath:kakao.properties")
                .run(args);
    }
}
