package com.ethan.web.webmvc;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication()
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableEurekaClient
@EnableCaching
public class WebEhcatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebEhcatchApplication.class, args);
    }

}
