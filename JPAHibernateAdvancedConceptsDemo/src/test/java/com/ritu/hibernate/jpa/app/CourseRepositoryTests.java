package com.ritu.hibernate.jpa.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.ritu.hibernate.jpa.app.entity.Course;
import com.ritu.hibernate.jpa.app.entity.Review;
import com.ritu.hibernate.jpa.app.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTests {
	
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Test
	public void find_by_Id_basic() {
		Course course =courseRepo.findById(1002L);
		assertEquals("English",course.getName());
	}
	
	@Test
	@DirtiesContext
	public void deleteById_basic() {
		logger.info("Delete test is running...");
		courseRepo.deletebyId(1003L);
		assertNull(courseRepo.findById(1003L));
	}
	
	@Test
	@DirtiesContext
	public void updateById_basic() {
		logger.info("Update by ID test is running...");
		Course course =courseRepo.updateById(1003L,"Games");		
		assertEquals("Games",course.getName());
	}

	@Test
	@DirtiesContext
	public void save_basic() {
		logger.info("Save/Update test is running...");
		// update details
		Course course = courseRepo.findById(1004L);
		assertEquals("Science", course.getName());
		course.setName("Games");
		courseRepo.save(course);
		Course course1 = courseRepo.findById(1004L);
		assertEquals("Games", course1.getName());

	}

	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		courseRepo.playWithEntityManager();

	}
	
	@Test
	@Transactional
	public void getReviewsOnCourses() {
		Course course = courseRepo.findById(1001L);
		logger.info("Reviews on the course-->{}" + course.getReviews());
	}

//taking shortcut in this test method , using entity manager to call a method on review(ideally should be in the review repository)
	@Test
	@Transactional
	public void getCoursefromReview() {
		Review review = em.find(Review.class, 5001L);
		logger.info("Reviews on the course-->{}" + review.getCourse());
	}


}
