package com.jy.study.udemy.jpahibernatespringboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"courses", "passport"})
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @ManyToMany
    //Join Table 이름 직접 지정.
    @JoinTable(name = "STUDENT_COURSE",
            //Join Table 컬럼명 지정.
            joinColumns = @JoinColumn(name="STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name="COURSE_ID")
    )
    private List<Course> courses = new ArrayList<>();

    protected Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }


}
