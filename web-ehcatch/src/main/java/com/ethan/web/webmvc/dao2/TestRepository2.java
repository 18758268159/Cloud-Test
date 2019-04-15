package com.ethan.web.webmvc.dao2;

import com.ethan.web.webmvc.dao.TestEntity;
import com.ethan.web.webmvc.dao.TestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestRepository2 extends TestRepository {

    @Query(value = "SELECT d FROM TestEntity d")
    List<TestEntity> getUserInfo();

}
