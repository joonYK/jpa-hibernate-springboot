package com.jy.study.udemy.jpahibernatespringboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    /*
     * student와 양방향 매핑이지만, 관계의 주체는 student이다.
     * mappedBy에 의해 passport에는 student의 PK가 FK로 저장되지 않음.
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;

    protected Passport() {
    }

    public Passport(String number) {
        this.number = number;
    }


}
