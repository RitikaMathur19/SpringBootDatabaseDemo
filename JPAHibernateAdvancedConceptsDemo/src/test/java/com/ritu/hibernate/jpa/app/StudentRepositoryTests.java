package com.ritu.hibernate.jpa.app;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.ritu.hibernate.jpa.app.entity.Passport;
import com.ritu.hibernate.jpa.app.entity.Student;
import com.ritu.hibernate.jpa.app.repository.StudentRepository;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateAdvancedConceptsDemoApplication.class)
class StudentRepositoryTests {

	@Autowired
	StudentRepository studentRepo;
	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**@Transactional is required so that the entire function is in a one complete transaction 
	 * It also creates a Persistence context
	 * Only at the end of the transaction all changes at the end of the transaction*/
	@Test
	@Transactional 
	public void retrieveStudentAndPassportDetails() {
		
		Student student = em.find(Student.class, 2001L);
		logger.info("Student..."+student);
		logger.info("Passport..."+student.getPassport());

	}
	

	@Test
	@Transactional
	public void retrievePassportDetails() {
		
		Passport passport = em.find(Passport.class, 4002L);
		
		logger.info("This Passport belongs to -->"+passport.getStudent());

	}
	
	@Test
	//Transactional at the repository level
	public void testToUnderstandPersistenceContext() {
		
		studentRepo.dummyOperationToUnderstandPersistenceContext();

	}
	

	}
