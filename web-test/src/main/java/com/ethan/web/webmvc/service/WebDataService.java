package com.ethan.web.webmvc.service;

import com.ethan.web.webmvc.dao.AdArticleEntity;

import java.util.List;

public interface WebDataService {
    List<AdArticleEntity> selectAll();
    List<AdArticleEntity> getWebMvcData();
}
