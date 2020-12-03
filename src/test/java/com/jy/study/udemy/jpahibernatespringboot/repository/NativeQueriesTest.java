package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@SpringBootTest
public class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void native_queries_basic() {
        Query query = em.createNativeQuery("select * from course", Course.class);
        List resultList = query.getResultList();
        logger.info("select * from course -> {}", resultList);
    }

    @Test
    public void native_queries_with_parameter() {
        Query query = em.createNativeQuery("select * from course where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("select * from course where id = ? -> {}", resultList);
    }

    @Test
    public void native_queries_with_named_parameter() {
        Query query = em.createNativeQuery("select * from course where id = :id", Course.class);
        query.setParameter("id", 10001L);
        List resultList = query.getResultList();
        logger.info("select * from course where id = :id -> {}", resultList);
    }

    @Test
    @Transactional
    public void native_queries_to_update() {
        Query query = em.createNativeQuery("update course set last_updated_date=current_timestamp()", Course.class);
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
    }


}
