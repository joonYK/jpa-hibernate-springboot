package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
public class CourseRepository {

    private final EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    //insert or update
    public Course save(Course course) {
        if (course.getId() == null)
            em.persist(course);
        else
            em.merge(course);

        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Web Services in 100 Steps");
        em.persist(course1);
        Course course2 = new Course("Angular Js in 100 Steps");
        em.persist(course2);

        //변경 사항이 데이터베이스에 전송되도록
        em.flush();

        course1.setName("Web Services in 100 Steps - Updated");
        course2.setName("Angular Js in 100 Steps - Updated");

        //데이터베이스에 있는 데이터를 조회해서 엔티티를 갱신.
        //엔티티 매니저에 있는 엔티티는 데이터베이스에 있던 데이터로 수정된다. setName 으로 수정했던 내용은 날아감.
        em.refresh(course1);

        em.flush();
    }
}
