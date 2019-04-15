package com.ethan.web.webmvc.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MapCache<k, v> {

    // 存放缓存容器
    public Map<k, v> concurrentHashMap = new ConcurrentHashMap<k, v>();

    // 存储
    public void put(k k, v v) {
        concurrentHashMap.put(k, v);
    }

    // 获取
    public v get(k k) {
        return concurrentHashMap.get(k);
    }

    // 删除
    public void remove(k k) {
        concurrentHashMap.remove(k);
    }


}
