package com.ethan.web.webmvc.dao;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * 数据库字段user,属性名称user
     * @param user 
     */
    List<UserEntity> findByUser(String user);

    /**
     * 数据库字段age,属性名称age
     * @param age 
     */
    List<UserEntity> findByAge(Integer age);

    /**
     * 数据库字段account,属性名称account
     * @param account 
     */
    List<UserEntity> findByAccount(String account);

    /**
     * 数据库字段passworld,属性名称passworld
     * @param passworld 
     */
    List<UserEntity> findByPassworld(String passworld);

    /**
     * 数据库字段id,属性名称id
     * @param ids 
     */
    List<UserEntity> findByIdIn(Collection<Integer> ids);

    /**
     * 数据库字段user,属性名称user
     * @param users 
     */
    List<UserEntity> findByUserIn(Collection<String> users);

    /**
     * 数据库字段age,属性名称age
     * @param ages 
     */
    List<UserEntity> findByAgeIn(Collection<Integer> ages);

    /**
     * 数据库字段account,属性名称account
     * @param accounts 
     */
    List<UserEntity> findByAccountIn(Collection<String> accounts);

    /**
     * 数据库字段passworld,属性名称passworld
     * @param passworlds 
     */
    List<UserEntity> findByPassworldIn(Collection<String> passworlds);
}