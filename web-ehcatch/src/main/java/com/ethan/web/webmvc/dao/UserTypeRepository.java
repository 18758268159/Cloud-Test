package com.ethan.web.webmvc.dao;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeEntity, Integer> {
    /**
     * 数据库字段userId,属性名称userid
     * @param userid 
     */
    List<UserTypeEntity> findByUserid(Integer userid);

    /**
     * 数据库字段type,属性名称type
     * @param type 
     */
    List<UserTypeEntity> findByType(String type);

    /**
     * 数据库字段userId,属性名称userid
     * @param userids 
     */
    List<UserTypeEntity> findByUseridIn(Collection<Integer> userids);

    /**
     * 数据库字段type,属性名称type
     * @param types 
     */
    List<UserTypeEntity> findByTypeIn(Collection<String> types);
}