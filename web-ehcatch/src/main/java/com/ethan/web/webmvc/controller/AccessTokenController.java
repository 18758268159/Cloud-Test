package com.ethan.web.webmvc.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.ethan.web.webmvc.base.BaseApiService;
import com.ethan.web.webmvc.base.ResponseBase;
import com.ethan.web.webmvc.dao.AppEntity;
import com.ethan.web.webmvc.dao.AppRepository;
import com.ethan.web.webmvc.service.RedisService;
import com.ethan.web.webmvc.utils.TokenUtils;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 使用令牌方式搭建
 */
@RestController
public class AccessTokenController extends BaseApiService {

    @Autowired
    private AppRepository appRepository;
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "获取AccessToken", notes = "获取AccessToken")
    @ResponseBody
    @RequestMapping(value = "/getAccessToken", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseBase getAccessToken(AppEntity appEntityp) throws JSONException {
        // 获取生成对应的Appid和AppSecret
        AppEntity data = appRepository.findByAppId(appEntityp.getAppId()).get(0);
        // 验证当前的Appid和AppSecret是否可用
        if (data.getIsFlag().equals("1") && appEntityp.getAppSecret().equals(data.getAppSecret()) && appEntityp.getAccessToken().equals(data.getAccessToken())) {
            // 使用Appid和AppSecret 生成 AccessToken 需要和第二步在同一个事物
            String accessToken = TokenUtils.getAccessToken();
            // 删除之前的 AccessToken
            AppEntity updataData = appRepository.findByAppId(appEntityp.getAppId()).get(0);
            updataData.setAccessToken(accessToken);
            appRepository.save(updataData);
            // 删除之前老的token
            redisService.delete(appEntityp.getAccessToken());
            // 缓存新生成的token
            redisService.set(accessToken, data.getAppId(), 60l);
            // 返回最新的 AccessToken
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("accessToken", accessToken);
            return setResultSuccessData(jSONObject);
        } else {
            return setResultError("当前AccessToken不可用!");
        }
    }

    @ApiOperation(value = "获取当前调用者的用户信息", notes = "获取当前调用者的用户信息")
    @ResponseBody
    @RequestMapping(value = "/getAppUser", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseBase getAppUser(String accessToken) {
        if (StringUtil.isNullOrEmpty(accessToken)) {
            return setResultError("accessToken is null");
        } else {
            String appId = redisService.get(accessToken);
            if (StringUtil.isNullOrEmpty(appId)) {
                return setResultError("accessToken is invalid");
            } else {
                AppEntity data = appRepository.findByAppId(appId).get(0);
                if (data.getIsFlag().equals("0")) {
                    return setResultError("权限不足!");
                } else {
                    // 返回当前调用者的用户信息
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("dataApp", data);
                    return setResultSuccessData(jSONObject);
                }
            }
        }
    }
}
