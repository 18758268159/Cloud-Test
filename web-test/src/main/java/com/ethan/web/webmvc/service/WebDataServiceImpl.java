package com.ethan.web.webmvc.service;

import com.ethan.web.webmvc.dao.*;
import com.ethan.web.webmvc.feign.WebMvcFeign;
import com.ethan.web.webmvc.service.WebDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WebDataServiceImpl implements WebDataService {

    @Autowired
    private AdArticleRepository adArticleRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<AdArticleEntity> selectAll() {
        List<AdArticleEntity> allData = adArticleRepository.findAll();
        return allData;
    }

    /**
     * 调用web-mvc的 test1 方法
     * 使用的是 rest 客户端调用工具
     */
    @Override
    public List<AdArticleEntity> getWebMvcData(){
        return restTemplate.getForObject("http://web-mvc/test1"  , List.class);
    }

}
