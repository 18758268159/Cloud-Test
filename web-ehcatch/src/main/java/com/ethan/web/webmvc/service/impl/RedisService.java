package com.ethan.web.webmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

// Spring 整合Redis
@Component
public class RedisService implements com.ethan.web.webmvc.service.RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String k, Object v, Long time) {
        // 让该方法支持多种数据类型的存放
        if (v instanceof String) {
            setString(k, v);
        } else if (v instanceof Set) {
            setSet(k, v);
        }

        if(time != null){
            // 设置有效期
            stringRedisTemplate.expire(k,time, TimeUnit.SECONDS);
        }
    }

    @Override
    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean delete(String key){
        return stringRedisTemplate.delete(key);
    }


    public void setString(String k, Object v) {
        String value = (String) v;
        // 表示存放String类型的数据
        stringRedisTemplate.opsForValue().set(k, value);
    }

    public void setSet(String k, Object v) {
        Set<String> value = (Set<String>) v;
        for (String string : value) {
            stringRedisTemplate.opsForSet().add(k, string);
        }
    }

}
