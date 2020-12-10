package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Course;
import com.jy.study.udemy.jpahibernatespringboot.entity.Passport;
import com.jy.study.udemy.jpahibernatespringboot.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
public class StudentRepository {

    private final EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    //insert or update
    public Student save(Student student) {
        if (student.getId() == null)
            em.persist(student);
        else
            em.merge(student);

        return student;
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public void saveStudentWIthPassport() {
        Passport passport = new Passport("Z123456");
        em.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
    }

    public void someOperationToUnderstandPersistenceContext() {
        /*
         * 영속성 컨텍스트는 하나의 트랜잭션내에서 엔티티들을 관리한다.
         * 2번 여권 조회시에 지연 로딩에 의해 DB에서 조회를 해야한다.
         * 하지만 1번 학생을 조회하고 영속성 컨텍스트가 종료되었기 때문에 세션 오류가 난다.
         */

        //Database Operation 1 - 학생 조회
        Student student = em.find(Student.class, 20001L);
        //영속성 컨텍스트 (student)

        //Database Operation 2 - 여권 조회
        Passport passport = student.getPassport();
        //영속성 컨텍스트 (student, passport)

        //Database Operation 3 - 여권 수정
        passport.setNumber("Z123457");
        //영속성 컨텍스트 (student, 수정된 passport)

        //Database Operation 4 - 학생 수정
        student.setName("Ranga - updated");
        //영속성 컨텍스트 (수정된 student, 수정된 passport)

        em.flush();
    }

    public void insertStudentAndCourse(Student student, Course course) {
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);
    }
}
