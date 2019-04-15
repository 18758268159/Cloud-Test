package com.ethan.web.webmvc.controller;

import com.ethan.web.webmvc.service.RedisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    RedisService redisService;

    @ApiOperation(value = "插入缓存", notes = "插入缓存")
    @ResponseBody
    @RequestMapping(value = "/setString", method = {RequestMethod.GET, RequestMethod.POST})
    public String setString(String key, String obj) {
        redisService.set(key, obj, 60l);
        return "success";
    }

    @ApiOperation(value = "获取缓存", notes = "获取缓存")
    @ResponseBody
    @RequestMapping(value = "/getString", method = {RequestMethod.GET, RequestMethod.POST})
    public String getString(String key) {
        return redisService.get(key);
    }

    @ApiOperation(value = "删除缓存", notes = "删除缓存")
    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String delete(String key) {
        if (redisService.delete(key)) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

}
