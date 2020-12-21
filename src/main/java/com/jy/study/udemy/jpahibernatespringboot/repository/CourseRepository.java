package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Course;
import com.jy.study.udemy.jpahibernatespringboot.entity.Review;
import com.jy.study.udemy.jpahibernatespringboot.entity.ReviewRating;
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
public class CourseRepository {

    Logger logger = LoggerFactory.getLogger(getClass());

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

        Course course2 = findById(10001L);
        course2.setName("Angular Js in 100 Steps - Updated");
    }

    public void addHardcodedReviewsForCourse() {
        //get the course 10003
        Course course = findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());

        //add 2 reviews to it
        Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on Stuff.");
        Review review2 = new Review(ReviewRating.FIVE, "Hatsoff.");

        //setting the relationship
        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        //save it to the database
        em.persist(review1);
        em.persist(review2);
    }

    public void addReviewsForCourse(long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("course.getReviews() -> {}", course.getReviews());

        for (Review review : reviews) {
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
    }
}
