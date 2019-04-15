package com.ethan.web.webmvc.dao;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<AppEntity, Integer> {
    /**
     * 数据库字段app_name,属性名称appName
     * @param appName 机构名称
     */
    List<AppEntity> findByAppName(String appName);

    /**
     * 数据库字段app_id,属性名称appId
     * @param appId 应用id
     */
    List<AppEntity> findByAppId(String appId);

    /**
     * 数据库字段app_secret,属性名称appSecret
     * @param appSecret  应用密钥  （可更改）
     */
    List<AppEntity> findByAppSecret(String appSecret);

    /**
     * 数据库字段is_flag,属性名称isFlag
     * @param isFlag 是否可用 （是否对某个机构开放）
     */
    List<AppEntity> findByIsFlag(String isFlag);

    /**
     * 数据库字段access_token,属性名称accessToken
     * @param accessToken 上一次access_token
     */
    List<AppEntity> findByAccessToken(String accessToken);

    /**
     * 数据库字段id,属性名称id
     * @param ids 
     */
    List<AppEntity> findByIdIn(Collection<Integer> ids);

    /**
     * 数据库字段app_name,属性名称appName
     * @param appNames 机构名称
     */
    List<AppEntity> findByAppNameIn(Collection<String> appNames);

    /**
     * 数据库字段app_id,属性名称appId
     * @param appIds 应用id
     */
    List<AppEntity> findByAppIdIn(Collection<String> appIds);

    /**
     * 数据库字段app_secret,属性名称appSecret
     * @param appSecrets  应用密钥  （可更改）
     */
    List<AppEntity> findByAppSecretIn(Collection<String> appSecrets);

    /**
     * 数据库字段is_flag,属性名称isFlag
     * @param isFlags 是否可用 （是否对某个机构开放）
     */
    List<AppEntity> findByIsFlagIn(Collection<String> isFlags);

    /**
     * 数据库字段access_token,属性名称accessToken
     * @param accessTokens 上一次access_token
     */
    List<AppEntity> findByAccessTokenIn(Collection<String> accessTokens);
}