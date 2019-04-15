package com.ethan.web.webmvc.controller;

import com.ethan.web.webmvc.dao.AdArticleEntity;
import com.ethan.web.webmvc.feign.WebMvcFeign;
import com.ethan.web.webmvc.service.WebDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private WebDataService webDataService;

    @Value("${userName}")
    private String userName;

    @Autowired
    private WebMvcFeign webMvcFeign;

    private int a = 0;

    @ApiOperation(value = "获取项目名称", notes = "获取项目名称")
    @ResponseBody
    @RequestMapping(value = "/getProjetName", method = {RequestMethod.GET, RequestMethod.POST})
    public String getProjetName() {
        return "工程名字是：web-test";
    }


    @ResponseBody
    @RequestMapping(value = "/test1", method = {RequestMethod.GET, RequestMethod.POST})
    public List<AdArticleEntity> getData() {
        List<AdArticleEntity> data = webDataService.selectAll();
        return data;
    }

    /**
     * 通过 eureka注册中心 向web-mvc 项目调用接口获取数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWebMvcData", method = {RequestMethod.GET, RequestMethod.POST})
    public List<AdArticleEntity> getWebMvcData() {
        List<AdArticleEntity> data = webDataService.getWebMvcData();
        System.out.print("getWebMvcData" + a++ + "\n");
        return data;
    }

    /**
     * 测试 service-zuul 网关   同一个人域名访问不同的 项目工程
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getWebTestApi", method = {RequestMethod.GET, RequestMethod.POST})
    public String getWebTestApi() {
        return "this is Web-Test  项目工程";
    }


    /**
     * server-config 配置中心
     * 获取 userName 值
     *
     * http://localhost:2003/api-web-test/getServerConfigUserName?token=1111
     *
     */
    @ApiOperation(value = "配置中心", notes = "server-config 配置中心   获取 userName 值")
    @ResponseBody
    @RequestMapping(value = "/getServerConfigUserName", method = {RequestMethod.GET, RequestMethod.POST})
    public String getServerConfigUserName() {
        return "userName value = " + userName;
    }

    /**
     * Feign 客户端调用测试
     * http://localhost:2003/api-web-test/getWebMvcDataTest?token=1111
     */
    @ResponseBody
    @RequestMapping(value = "/getWebMvcDataTest", method = {RequestMethod.GET, RequestMethod.POST})
    public String getWebMvcDataTest(){
        return "调用了web-mvc的 test 接口 >>>> " + webMvcFeign.getWebMvcDataTest();
    }

    /**
     * Feign 客户端调用测试
     * http://localhost:2003/api-web-test/getWebMvcDataTest1?token=1111
     */
    @ResponseBody
    @RequestMapping(value = "/getWebMvcDataTest1", method = {RequestMethod.GET, RequestMethod.POST})
    public List<AdArticleEntity> getWebMvcDataTest1(){
        return webMvcFeign.getWebMvcDataTest1();
    }

    /**
     * Hystrix 熔断机制调用测试
     * http://localhost:2003/api-web-test/testHystrix?token=1111   // sleep 未成功
     */
    @ResponseBody
    @RequestMapping(value = "/testHystrix", method = {RequestMethod.GET, RequestMethod.POST})
    public String testHystrix(){
        return webMvcFeign.testHystrix();
    }

}
