package com.ethan.web.webmvc.dao;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdArticleRepository extends JpaRepository<AdArticleEntity, Integer> {
    /**
     * 数据库字段dd_id,属性名称ddId
     * @param ddId 
     */
    List<AdArticleEntity> findByDdId(Integer ddId);

    /**
     * 数据库字段dd_type_code,属性名称ddTypeCode
     * @param ddTypeCode 
     */
    List<AdArticleEntity> findByDdTypeCode(Integer ddTypeCode);

    /**
     * 数据库字段dd_code,属性名称ddCode
     * @param ddCode 
     */
    List<AdArticleEntity> findByDdCode(Integer ddCode);

    /**
     * 数据库字段dd_name,属性名称ddName
     * @param ddName 
     */
    List<AdArticleEntity> findByDdName(String ddName);

    /**
     * 数据库字段dd_description,属性名称ddDescription
     * @param ddDescription 
     */
    List<AdArticleEntity> findByDdDescription(String ddDescription);

    /**
     * 数据库字段dd_expand,属性名称ddExpand
     * @param ddExpand 
     */
    List<AdArticleEntity> findByDdExpand(String ddExpand);

    /**
     * 数据库字段dd_id,属性名称ddId
     * @param ddIds 
     */
    List<AdArticleEntity> findByDdIdIn(Collection<Integer> ddIds);

    /**
     * 数据库字段dd_type_code,属性名称ddTypeCode
     * @param ddTypeCodes 
     */
    List<AdArticleEntity> findByDdTypeCodeIn(Collection<Integer> ddTypeCodes);

    /**
     * 数据库字段dd_code,属性名称ddCode
     * @param ddCodes 
     */
    List<AdArticleEntity> findByDdCodeIn(Collection<Integer> ddCodes);

    /**
     * 数据库字段dd_name,属性名称ddName
     * @param ddNames 
     */
    List<AdArticleEntity> findByDdNameIn(Collection<String> ddNames);

    /**
     * 数据库字段dd_description,属性名称ddDescription
     * @param ddDescriptions 
     */
    List<AdArticleEntity> findByDdDescriptionIn(Collection<String> ddDescriptions);

    /**
     * 数据库字段dd_expand,属性名称ddExpand
     * @param ddExpands 
     */
    List<AdArticleEntity> findByDdExpandIn(Collection<String> ddExpands);
}