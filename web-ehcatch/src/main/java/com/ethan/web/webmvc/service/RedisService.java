package com.ethan.web.webmvc.service;

import org.apache.ibatis.annotations.Select;

public interface RedisService{
    void set(String k , Object v , Long time);
    String get(String key);
    Boolean delete(String key);
}
