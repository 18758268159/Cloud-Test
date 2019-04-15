package com.ethan.web.webmvc.service;

import com.ethan.web.webmvc.dao.*;
import com.ethan.web.webmvc.service.WebDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebDataServiceImpl implements WebDataService {

    @Autowired
    private AdArticleRepository adArticleRepository;

    @Override
    public List<AdArticleEntity> selectAll() {
//        AdArticleEntityExample adArticleEntityExample = new AdArticleEntityExample();
//        adArticleEntityExample.getOredCriteria();
        List<AdArticleEntity> allData = adArticleRepository.findAll();
        return allData;
    }

}
