package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent() {
        Optional<Course> courseOptional   = repository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent() {
        Optional<Course> courseOptional = repository.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void playingAroundWithSpringDataRepository() {
        Course course = new Course("Microservices in 100 Steps");
        repository.save(course);

        course.setName("Microservices in 100 Steps - Updated");
        repository.save(course);

        logger.info("Courses -> {}", repository.findAll());
        logger.info("count -> {}", repository.count());
    }

    @Test
    public void sort() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        logger.info("Courses -> {}", repository.findAll(sort));
    }

    @Test
    public void pagination() {
        repository.saveAll(
                IntStream.range(1, 10)
                        .mapToObj(i -> new Course(String.format("dummy%d", i)))
                        .collect(Collectors.toList())
        );

        //첫번째 페이지, 5개씩
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First Page -> {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Second Page -> {}", secondPage.getContent());

    }

    @Test
    @Transactional
    public void customQuery() {
        //findByNameAndId
        Course course = repository.findByNameAndId("JPA in 50 Steps", 10001L);
        assertEquals(course.getName(), "JPA in 50 Steps");

        //findByName
        List<Course> byName = repository.findByName("Spring in 50 Steps");
        assertEquals(byName.get(0).getName(), "Spring in 50 Steps");

        //countByName
        Integer countByName = repository.countByName("Spring Boot in 100 Steps");
        assertEquals(countByName, 1);

        //findAllByOrderByIdDesc
        List<Course> allOrderByIdDesc = repository.findAllByOrderByIdDesc();
        assertEquals(allOrderByIdDesc.get(0).getId(), 10003);
        assertEquals(allOrderByIdDesc.get(1).getId(), 10002);
        assertEquals(allOrderByIdDesc.get(2).getId(), 10001);

        //courseWith100StepsInName
        List<Course> courseWIth100StepsInName = repository.courseWith100StepsInName();
        assertEquals(courseWIth100StepsInName.size(), 1);

        //courseWith100StepsInNameUsingNativeQuery
        List<Course> courseWith100StepsInNameUsingNativeQuery = repository.courseWith100StepsInNameUsingNativeQuery();
        assertEquals(courseWith100StepsInNameUsingNativeQuery.size(), 1);

        //courseWith100StepsInNameUsingNamedQuery
        List<Course> courseWith100StepsInNameUsingNamedQuery = repository.courseWith100StepsInNameUsingNamedQuery();
        assertEquals(courseWith100StepsInNameUsingNamedQuery.size(), 3);

        //deleteByName
        repository.deleteByName("JPA in 50 Steps");
        List<Course> deleteCheck = repository.findByName("JPA in 50 Steps");
        assertTrue(deleteCheck.isEmpty());
    }
}