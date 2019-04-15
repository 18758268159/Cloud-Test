package com.ethan.web.webmvc.controller;

import com.ethan.web.webmvc.dao.AdArticleEntity;
import com.ethan.web.webmvc.service.WebDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private WebDataService webDataService;

    @Value("${server.port}")
    private String serverPort;

    @ApiOperation(value = "获取项目名称", notes = "获取项目名称")
    @ResponseBody
    @RequestMapping(value = "/getProjetName", method = {RequestMethod.GET, RequestMethod.POST})
    public String getProjetName() {
        return "工程名字是：web-mvc";
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public String queryData() {
        return "port-" + serverPort;
    }

    @ResponseBody
    @RequestMapping(value = "/test1", method = {RequestMethod.GET, RequestMethod.POST})
    public List<AdArticleEntity> getData() {
        List<AdArticleEntity> data = webDataService.selectAll();
        return data;
    }

    /**
     * 测试 service-zuul 网关   同一个人域名访问不同的 项目工程
     *
     * http://localhost:2003/api-web-test/getServerConfigUserName?token=1111
     *
     */
    @ResponseBody
    @RequestMapping(value = "/getWebMvcApi", method = {RequestMethod.GET, RequestMethod.POST})
    public String getWebMvcApi() {
        return "this is Web-MVC  项目工程";
    }


    /**
     *  Hystrix熔断机制的测试 当一个请求调用超过三秒的fallBack操作
     */
    @ResponseBody
    @RequestMapping(value = "/testHystrix", method = {RequestMethod.GET, RequestMethod.POST})
    public String testHystrix() {

//        try {
//          Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        try {
//            Thread.sleep(1000); //1000 毫秒，也就是1秒.
//        } catch(InterruptedException ex) {
//            Thread.currentThread().interrupt();
//        }

        return "请求使用了三秒";
    }


}
