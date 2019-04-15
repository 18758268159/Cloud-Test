package com.ethan.web.webmvc.controller;

import com.ethan.web.webmvc.base.BaseApiService;
import com.ethan.web.webmvc.base.ResponseBase;
import com.ethan.web.webmvc.dao.UserEntity;
import com.ethan.web.webmvc.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController extends BaseApiService {

    @Autowired
    private UserService userService;
    @Autowired
    private CacheManager cacheManager;

    @ApiOperation(value = "插入数据", notes = "插入数据")
    @ResponseBody
    @RequestMapping(value = "/insertData", method = {RequestMethod.GET, RequestMethod.POST})
    public void getProjetName(String name) {
        userService.insertData(name);
    }

    @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
    @ResponseBody
    @RequestMapping(value = "/getAll", method = {RequestMethod.GET, RequestMethod.POST})
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @ApiOperation(value = "获取所有数据", notes = "获取所有数据")
    @ResponseBody
    @RequestMapping(value = "/getDataById", method = {RequestMethod.GET, RequestMethod.POST})
    public Optional<UserEntity> getDataById(int id) {
        return userService.getDataById(id);
    }

    @ApiOperation(value = "清除缓存", notes = "清除缓存")
    @RequestMapping(value = "/clearCache" ,  method = {RequestMethod.GET})
    public void remoKey() {
        cacheManager.getCache("userCache").clear();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/userLogin" ,  method = {RequestMethod.GET})
    public ResponseBase userLogin(String account , String password) {
        return setResultSuccess("登陆成功");
    }

    @ApiOperation(value = "基于视图查询的--连表查询获取用户信息", notes = "基于视图查询的--连表查询获取用户信息")
    @RequestMapping(value = "/getUserInfo" ,  method = {RequestMethod.GET})
    public ResponseBase userLogin() {
        return setResult(1 , "连表查询获取用户信息成功!" , userService.getUserInfo());
    }

    @ApiOperation(value = "用户信息更新成功!", notes = "用户信息更新成功!")
    @RequestMapping(value = "/updataUserInfo" ,  method = {RequestMethod.GET})
    public ResponseBase updataUserInfo() {
        userService.updataUserInfo();
        return setResultSuccess( "用户信息更新成功!");
    }

}
