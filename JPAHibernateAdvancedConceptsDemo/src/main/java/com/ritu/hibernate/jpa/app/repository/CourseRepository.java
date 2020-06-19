package com.ritu.hibernate.jpa.app.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ritu.hibernate.jpa.app.entity.Course;

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

}
