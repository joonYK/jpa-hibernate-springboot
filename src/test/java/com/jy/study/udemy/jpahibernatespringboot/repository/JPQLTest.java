package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Course;
import com.jy.study.udemy.jpahibernatespringboot.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
public class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic() {
        //From Course는 데이터베이스 테이블의 course가 아닌 자바의 엔티티 클래스.
        Query query = em.createQuery("Select c From Course c");
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100 Steps'", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c where name like '%100 Steps' -> {}", resultList);
    }

    @Test
    public void jpql_courses_without_students() {
        //From Course는 데이터베이스 테이블의 course가 아닌 자바의 엔티티 클래스.
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    @Test
    public void namedQuery_all_courses() {
        //From Course는 데이터베이스 테이블의 course가 아닌 자바의 엔티티 클래스.
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("name = \"query_get_all_courses\", query = \"Select c From Course c\" -> {}", resultList);
    }

    @Test
    public void namedQuery_where_like_100_Step() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_100_Step_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("name = \"query_get_100_Step_courses\", query = \"Select c From Course c where name like '%100 Steps'\" -> {}", resultList);
    }

    @Test
    public void jpql_courses_with_atleast_2_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    @Test
    public void jpql_courses_ordered_by_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    @Test
    public void jpql_students_with_passports_in_a_certain_pattern() {
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%1234%'", Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

}
