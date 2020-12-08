package com.jy.study.udemy.jpahibernatespringboot;

import com.jy.study.udemy.jpahibernatespringboot.repository.CourseRepository;
import com.jy.study.udemy.jpahibernatespringboot.repository.StudentRepository;
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
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //courseRepository.playWithEntityManager();
        //studentRepository.saveStudentWIthPassport();
        courseRepository.addReviewsForCourse();
    }
}
