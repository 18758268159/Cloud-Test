package com.ethan.web.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication()
@EnableEurekaClient
@EnableFeignClients
public class WebTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTestApplication.class, args);
    }

    @Bean          // 将 RestTemplate 注入Bean
    @LoadBalanced  // 支持负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
