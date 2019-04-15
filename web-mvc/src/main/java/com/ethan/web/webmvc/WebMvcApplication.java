package com.ethan.web.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WebMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebMvcApplication.class, args);
    }
}
