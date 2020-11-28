package com.jy.study.udemy.jpahibernatespringboot;

import com.jy.study.udemy.jpahibernatespringboot.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaHibernateSpringbootApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJdbcDao personJdbcDao;

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All users -> {}", personJdbcDao.findAll());
        logger.info("User id 10001 -> {}", personJdbcDao.findById(10001));
        logger.info("Deleting 10002 -> No of Rows Deleted - {}", personJdbcDao.deleteById(10002));
    }
}
