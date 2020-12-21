package com.jy.study.udemy.jpahibernatespringboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReviewRating rating;

    private String description;

    @ManyToOne
    private Course course;

    protected Review() {
    }

    public Review(ReviewRating rating, String description) {
        this.rating = rating;
        this.description = description;
    }
}
