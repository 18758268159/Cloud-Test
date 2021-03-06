package com.ethan.web.webmvc.controller;

import com.ethan.web.webmvc.cache.MapCache;
import com.ethan.web.webmvc.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class MapCatchController {

    @Autowired
    private MapCache<String , Object> mapCache;

    @ResponseBody
    @RequestMapping(value = "/put", method = {RequestMethod.GET, RequestMethod.POST})
    public void put(String key , String value) {
        mapCache.put(key , value);
    }

    @ResponseBody
    @RequestMapping(value = "/putList", method = {RequestMethod.GET, RequestMethod.POST})
    public void put(String key) {

//        LoggerFactory
        List<String> data = new ArrayList<>();
        data.add("张三");
        data.add("张三");
        data.add("张三");
        mapCache.put(key , data);

    }

    @ResponseBody
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    public Object get(String key) {
      return mapCache.get(key).toString();
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = {RequestMethod.GET, RequestMethod.POST})
    public void remove(String key) {
        mapCache.remove(key);
    }


    /**
     * SpringContextUtil 测试
     */
    @ResponseBody
    @RequestMapping(value = "/getSpringContext", method = {RequestMethod.GET, RequestMethod.POST})
    public void remove() {
        log.info("aaaaaa");
        ApplicationContext applicationContext =  SpringContextUtil.getApplicationContext();
    }

}
