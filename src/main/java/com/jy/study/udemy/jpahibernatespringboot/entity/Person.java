package com.jy.study.udemy.jpahibernatespringboot.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name="find_all_persons", query = "select p from Person p")
public class Person {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String location;

    private Date birthDate;

    public Person(String name, String location, Date birthDate) {
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "\nPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
