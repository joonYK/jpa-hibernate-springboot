package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Employee;
import com.jy.study.udemy.jpahibernatespringboot.entity.FullTimeEmployee;
import com.jy.study.udemy.jpahibernatespringboot.entity.PartTimeEmployee;
import com.jy.study.udemy.jpahibernatespringboot.entity.Student;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@Transactional
@RequiredArgsConstructor
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final EntityManager em;

    public void insert(Employee employee) {
        em.persist(employee);
    }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
        return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
        return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }

}
