package com.ritu.hibernate.jpa.app.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ritu.hibernate.jpa.app.entity.Passport;
import com.ritu.hibernate.jpa.app.entity.Student;


@Repository
@Transactional
public class StudentRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	public void deletebyId(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public void save(Student student) {
		if(student.getId()==0) {
			em.persist(student);
		}
		else 
			em.merge(student);
	}
	public void saveStudentWithPassport() {
		logger.info("Student with passport--->");
		Passport passport =new Passport("Z78798");
		em.persist(passport);
		//first persist passport then establish relationship
		Student student1=new Student("Kaavya");
		student1.setPassport(passport);
		em.persist(student1);
		
	}

	
	@Transactional
	public void dummyOperationToUnderstandPersistenceContext() {
		
		//DB operation 1.  retrieve student
		Student student = em.find(Student.class, 2001L);
		logger.info("Student..."+student);
		
		//DB operation 2.  retrieve passport
		Passport passport = student.getPassport();
		logger.info("Passport..."+passport);
		
		//DB operation 3.  update passport
		passport.setNumber(passport.getNumber());
		//DB operation 4.  update student 
		
		student.setName("Ritu  Mathur");
		logger.info("This Passport belongs to -->"+passport.getStudent());

	}
	
	
}
