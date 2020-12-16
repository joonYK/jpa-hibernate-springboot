package com.jy.study.udemy.jpahibernatespringboot.repository;

import com.jy.study.udemy.jpahibernatespringboot.entity.Course;
import com.jy.study.udemy.jpahibernatespringboot.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CourseRepositoryTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager em;

    @Test
    public void findById_basic() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @Test
    @Transactional
    public void findById_firstLevelCacheDemo() {
        Course course = repository.findById(10001L);
        logger.info("First Course Retrieved {}", course);

        //다시 DB에서 조회해오지 않고 영속성 컨텍스트에 저장되어있는 데이터를 가져온다. (1차 캐시)
        //@Transactional 으로 트랜잭션이 묶이지 않으면, 각 조회시마다 트랜잭션과 영속성 컨텍스트가 적용되기 때문에 1차 캐시를 이용한 조회는 사용할 수 없다.
        Course course1 = repository.findById(10001L);
        logger.info("First Course Retrieved again {}", course);

        assertEquals("JPA in 50 Steps", course.getName());
        assertEquals("JPA in 50 Steps", course1.getName());
    }

    @Test
    //애플리케이션 컨텍스트를 공유하지 않도록 적용. 뒤의 테스트에 영향 X
    @DirtiesContext
    public void deleteById_basic() {
        repository.deleteById(10002L);
        assertNull(repository.findById(10002L));
    }

    @Test
    public void save_basic() {
        //get a course
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());

        //update details
        course.setName("JPA in 50 Steps - Updated");
        repository.save(course);

        //check the value
        Course course1 = repository.findById(10001L);
        assertEquals("JPA in 50 Steps - Updated", course1.getName());
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager() {
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = repository.findById(10001L);
        /*
         * reviews 조회시, DB 에서 검색.
         * 일대다 양방향 매핑중에 Many에 해당하는 곳은 기본이 LAZY Fetching.
         */
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReview() {
        Review review = em.find(Review.class, 50001L);
        /*
         * DB에서 join으로 course까지 한번에 조회한다.
         * 일대다 양방향 매핑중에 One에 해당하는 곳은 기본이 EAGER Fetching.
         */
        logger.info("{}", review.getCourse());
    }

}
