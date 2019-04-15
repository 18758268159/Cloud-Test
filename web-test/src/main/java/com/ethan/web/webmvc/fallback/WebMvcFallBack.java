package com.ethan.web.webmvc.fallback;

import com.ethan.web.webmvc.dao.AdArticleEntity;
import com.ethan.web.webmvc.feign.WebMvcFeign;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebMvcFallBack implements WebMvcFeign {

    @Override
    public String getWebMvcDataTest() {
        // 服务降级处理
        return "服务发生异常!!!!!";
    }

    @Override
    public List<AdArticleEntity> getWebMvcDataTest1() {
        return null;
    }

    @Override
    public String testHystrix() {
        return "testHystrix 调用服务发生异常!!!!!";
    }

}
