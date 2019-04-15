package com.ethan.web.webmvc.service.impl;

import com.ethan.web.webmvc.dao.*;
import com.ethan.web.webmvc.dao2.TestRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "userCache")  // 表示创建缓存配置，Key为userCache
public class UserService implements com.ethan.web.webmvc.service.UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository2 testRepository;

    @Override
    public void insertData(String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUser(name);
        userEntity.setAccount("18758268159");
        userEntity.setAge(25);
//        userEntity.setId(id);
        userEntity.setPassworld("123456");
        userRepository.save(userEntity);
    }

    @Cacheable //  加了该注解的方法表示可以缓存
    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getDataById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void updataUserInfo() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setUser("ethan");
        userEntity.setAge(112);
        userEntity.setPassworld("1234567");
        userEntity.setAccount("177273635");
        userRepository.save(userEntity);
    }

    @Override
    public List<TestEntity> getUserInfo() {
        return testRepository.getUserInfo();
    }

}
