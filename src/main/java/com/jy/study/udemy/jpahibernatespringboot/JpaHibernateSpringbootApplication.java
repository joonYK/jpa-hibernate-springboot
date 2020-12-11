package com.jy.study.udemy.jpahibernatespringboot;

import com.jy.study.udemy.jpahibernatespringboot.entity.FullTimeEmployee;
import com.jy.study.udemy.jpahibernatespringboot.entity.PartTimeEmployee;
import com.jy.study.udemy.jpahibernatespringboot.repository.CourseRepository;
import com.jy.study.udemy.jpahibernatespringboot.repository.EmployeeRepository;
import com.jy.study.udemy.jpahibernatespringboot.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class JpaHibernateSpringbootApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //courseRepository.playWithEntityManager();
        //studentRepository.saveStudentWIthPassport();
        //courseRepository.addHardcodedReviewsForCourse();

        /*List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("5", "Great Hands-on Stuff."));
        reviews.add(new Review("5", "Hatsoff."));
        courseRepository.addReviewsForCourse(10003L, reviews);*/

        /*Student student = new Student("jack");
        Course course = new Course("Microservices in 100 Steps");
        studentRepository.insertStudentAndCourse(student, course);*/

        employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal(50)));
        employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal(10000)));
        logger.info("All employees -> {}", employeeRepository.retrieveAllEmployees());

    }
}
