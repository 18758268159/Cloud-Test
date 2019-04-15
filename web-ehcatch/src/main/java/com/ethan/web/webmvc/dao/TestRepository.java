package com.ethan.web.webmvc.dao;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer> {
    /**
     * 数据库字段user,属性名称user
     * @param user 
     */
    List<TestEntity> findByUser(String user);

    /**
     * 数据库字段account,属性名称account
     * @param account 
     */
    List<TestEntity> findByAccount(String account);

    /**
     * 数据库字段age,属性名称age
     * @param age 
     */
    List<TestEntity> findByAge(Integer age);

    /**
     * 数据库字段passworld,属性名称passworld
     * @param passworld 
     */
    List<TestEntity> findByPassworld(String passworld);

    /**
     * 数据库字段type,属性名称type
     * @param type 
     */
    List<TestEntity> findByType(String type);

    /**
     * 数据库字段user,属性名称user
     * @param users 
     */
    List<TestEntity> findByUserIn(Collection<String> users);

    /**
     * 数据库字段account,属性名称account
     * @param accounts 
     */
    List<TestEntity> findByAccountIn(Collection<String> accounts);

    /**
     * 数据库字段age,属性名称age
     * @param ages 
     */
    List<TestEntity> findByAgeIn(Collection<Integer> ages);

    /**
     * 数据库字段passworld,属性名称passworld
     * @param passworlds 
     */
    List<TestEntity> findByPassworldIn(Collection<String> passworlds);

    /**
     * 数据库字段type,属性名称type
     * @param types 
     */
    List<TestEntity> findByTypeIn(Collection<String> types);
}