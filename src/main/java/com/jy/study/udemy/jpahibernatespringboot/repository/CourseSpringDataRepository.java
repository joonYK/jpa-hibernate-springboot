package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//course resource에 대한 restful api를 쉽고 빠르게 생성.
@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    Course findByNameAndId(String name, Long id);

    List<Course> findByName(String name);

    Integer countByName(String name);

    List<Course> findAllByOrderByIdDesc();

    void deleteByName(String name);

    @Query("select c from Course c where name like '%100 Steps'")
    List<Course> courseWith100StepsInName();

    @Query(value="select * from Course c where name like '%100 Steps'", nativeQuery = true)
    List<Course> courseWith100StepsInNameUsingNativeQuery();

    @Query(name="query_get_all_courses")
    List<Course> courseWith100StepsInNameUsingNamedQuery();
}
