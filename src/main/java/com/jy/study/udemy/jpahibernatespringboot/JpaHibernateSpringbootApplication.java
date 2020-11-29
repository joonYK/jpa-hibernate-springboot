package com.jy.study.udemy.jpahibernatespringboot;

import com.jy.study.udemy.jpahibernatespringboot.entity.Person;
import com.jy.study.udemy.jpahibernatespringboot.jdbc.PersonJdbcDao;
import com.jy.study.udemy.jpahibernatespringboot.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JpaHibernateSpringbootApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJpaRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("User id 10001 -> {}", repository.findById(10001));

        logger.info("Inserting -> {}",
                repository.insert(new Person("Tara", "Berlin", new Date())));

        logger.info("Update 10003 -> {}",
                repository.update(new Person(10003, "Pieter", "Utrecht", new Date())));

        /*logger.info("All users -> {}",
                personJdbcDao.findAll());

        logger.info("Deleting 10002 -> No of Rows Deleted - {}",
                personJdbcDao.deleteById(10002));*/
    }
}
