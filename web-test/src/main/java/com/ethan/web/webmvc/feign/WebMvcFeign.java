package com.ethan.web.webmvc.feign;

import com.ethan.web.webmvc.dao.AdArticleEntity;
import com.ethan.web.webmvc.fallback.WebMvcFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "web-mvc" , fallback = WebMvcFallBack.class)
public interface WebMvcFeign {

    @RequestMapping("/test")
    public String getWebMvcDataTest();

    @RequestMapping("/test1")
    public List<AdArticleEntity> getWebMvcDataTest1();

    @RequestMapping("/testHystrix")
    public String testHystrix();


}

// 不使用熔断机制的写法
//@FeignClient(value = "web-mvc" , fallback = WebMvcFallBack.class)
//public interface WebMvcFeign {
//
//    @RequestMapping("/test")
//    public String getWebMvcDataTest();
//
//    @RequestMapping("/test1")
//    public List<AdArticleEntity> getWebMvcDataTest1();
//
//}
