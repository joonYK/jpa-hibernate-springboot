package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@SpringBootTest
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic() {
        //"Select c From Course c" 조회를 Criteria Query로 조회

        //1. CriteriaBuilder로 CriteriaQuery 생성.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. 쿼리와 관련된 테이블의 루트 정의 ("From Course c")
        Root<Course> courseRoot = cq.from(Course.class);

        //3. criteriaQuery를 사용해서 조회.
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void all_courses_having_100Steps() {
        //"Select c From Course c where name like '%100 Steps'"

        //1. CriteriaBuilder로 CriteriaQuery 생성.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. 쿼리와 관련된 테이블의 루트 정의 ("From Course c")
        Root<Course> courseRoot = cq.from(Course.class);

        //3. 검색 조건 정의
        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");
        cq.where(like100Steps);

        //4. criteriaQuery를 사용해서 조회.
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void all_courses_without_students() {
        //"Select c From Course c where c.students is empty"

        //1. CriteriaBuilder로 CriteriaQuery 생성.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. 쿼리와 관련된 테이블의 루트 정의 ("From Course c")
        Root<Course> courseRoot = cq.from(Course.class);

        //3. 검색 조건 정의
        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
        cq.where(studentsIsEmpty);

        //4. criteriaQuery를 사용해서 조회.
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void join() {
        //"Select c From Course c join c.students s"

        //1. CriteriaBuilder로 CriteriaQuery 생성.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. 쿼리와 관련된 테이블의 루트 정의 ("From Course c")
        Root<Course> courseRoot = cq.from(Course.class);

        //3. 검색 조건 정의
        Join<Object, Object> join = courseRoot.join("students");

        //4. criteriaQuery를 사용해서 조회.
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    public void left_join() {
        //"Select c From Course c left join c.students s"

        //1. CriteriaBuilder로 CriteriaQuery 생성.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        //2. 쿼리와 관련된 테이블의 루트 정의 ("From Course c")
        Root<Course> courseRoot = cq.from(Course.class);

        //3. 검색 조건 정의
        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        //4. criteriaQuery를 사용해서 조회.
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }


}
