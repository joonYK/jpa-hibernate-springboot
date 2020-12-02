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

        //엔티티 관리자가 변경 사항을 더이상 추적하지 않음.
        em.detach(course2);

        //엔티티 관리자에서 모든 엔티티 제거.
        //em.clear();

        course1.setName("Web Services in 100 Steps - Updated");
        em.flush();

        course2.setName("Angular Js in 100 Steps - Updated");
        em.flush();
    }
}
