package com.jy.study.udemy.jpahibernatespringboot.jpa;

import com.jy.study.udemy.jpahibernatespringboot.entity.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class PersonJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }
}
