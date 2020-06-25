package com.ritu.hibernate.jpa.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.hibernate.jpa.app.entity.Course;
import com.ritu.hibernate.jpa.app.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	// All CRUD operations on Course

	// Search a course
	public Course findById(Long id) {
		logger.info("Inside course repository****************");
		return em.find(Course.class, id);

	}

	// Delete a course
	public void deletebyId(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	// Save or update a course
	public void save(Course course) {
		if (course.getId() == 0) {
			em.persist(course);
		} else
			em.merge(course);
	}

	// debug and check values
	/**
	 * Exploring persist() , flush() , detach() , refresh() with reference to the
	 * Persistence Context
	 */
	public void playWithEntityManager() {
		Course course1 = new Course("Web Services");
		em.persist(course1);
		Course course2 = new Course("Angular JS");
		em.persist(course2);
		em.flush();
		// detach()-detaches the entity from the em and no changes to the entity are
		// persisted to the db
		// em.detach(course1);

		// clear() detaches all entities from the DB and no changes beyond this point
		// are persited to DB
		// em.clear();
		course1.setName("Web services to Microservices");
		course2.setName("Angular to react");
		em.refresh(course1);

		em.flush();
	}

	public Course updateById(Long id, String name) {
		Course course = findById(id);
		if (course != null)
			course.setName(name);
		Course updatedCourse = em.merge(course);
		em.flush();
		return updatedCourse;

	}

	// exploring relationships between entities
	public void addHardCodedReviewsForCourse() {
		// get the course

		Course course = findById(1001L);

		// add two reviews to the course

		Review review1 = new Review("5", "Great Learning");
		Review review2 = new Review("5", "Wow course");

		// add to the course
		course.addReviews(review1);
		course.addReviews(review2);
		// set the course in the review ,establishing relationships	
		review1.setCourse(course);
		review2.setCourse(course);
		//persisting the reviews
		em.persist(review1);
		em.persist(review2);

		logger.info("Reviews on the course:" + course + "-->" + course.getReviews());

	}

	public void addReviewForCourse(Long courseId, List<Review> reviews) {
		// get the course

		Course course = findById(courseId);
		// add reviews to the course
		for (Review review : reviews) {
			course.addReviews(review);
			review.setCourse(course);
			em.persist(review);
		}

		logger.info("Reviews on the course:" + course + "-->" + course.getReviews());

	}

}
