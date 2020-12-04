package com.jy.study.udemy.jpahibernatespringboot.repository;

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
}
