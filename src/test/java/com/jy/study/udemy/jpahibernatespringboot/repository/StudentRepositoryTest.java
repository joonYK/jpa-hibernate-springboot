package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Address;
import com.jy.study.udemy.jpahibernatespringboot.entity.Passport;
import com.jy.study.udemy.jpahibernatespringboot.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    @Test
    public void someTest() {
        repository.someOperationToUnderstandPersistenceContext();
    }

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails() {
        Student student = em.find(Student.class, 20001L);
        //Lazy fetch로 passport는 프록시 객체. db에서는 student만 조회하고 passport는 조회하지 않음.
        logger.info("student -> {}", student.getId());
        //Lazy fetch로 실제 passport를 사용할 때, 데이터베이스에서 조회해 온다.
        logger.info("passport -> {}", student.getPassport());

    }

    @Test
    @Transactional
    public void setAddressDetails() {
        Student student = em.find(Student.class, 20001L);
        student.setAddress(new Address("No 101", "Some Street", "Hyderabad"));
        em.flush();
        logger.info("student -> {}", student.getId());
        logger.info("passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("passport -> {}", passport);
        //student 사용 시, db 에서 조회.
        logger.info("student -> {}", passport.getStudent());

    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses() {
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        //ManyToMany 매핑 기본 fetch 전략은 LAZY
        logger.info("courses -> {}", student.getCourses());
    }

}
