package com.ethan.web.webmvc.service;

import com.ethan.web.webmvc.dao.TestEntity;
import com.ethan.web.webmvc.dao.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void insertData(String name);
    List<UserEntity> getAll();
    Optional<UserEntity> getDataById(int id);
    List<TestEntity> getUserInfo();
    void updataUserInfo();
}
