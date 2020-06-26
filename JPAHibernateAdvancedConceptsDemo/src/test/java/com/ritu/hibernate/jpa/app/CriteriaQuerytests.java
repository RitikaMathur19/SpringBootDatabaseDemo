package com.ritu.hibernate.jpa.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
//following import is for Junit 5
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ritu.hibernate.jpa.app.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateAdvancedConceptsDemoApplication.class)
public class CriteriaQuerytests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager em;
	
	
	@Test	 
	public void criteriaQuery_selectAll() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> courseRoot = cq.from(Course.class);
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultSet = query.getResultList();
		
		logger.info("Typed Query CriteriaAPI...All courses-->"+resultSet);
		

	}
	@Test	 
	public void criteriaQuery_likeQuery() {
		
		//1.use criteriaBuilder to create a Criteria query returning expected result set
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		//2.Define roots for tables involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3.Define Predicate etc using Criteria builder
		Predicate likeMaths = cb.like(courseRoot.get("name"),"Maths");
		//4.Add predicates etc to the Criteria Query
		cq.where(likeMaths);
		
		//5. Build the Typed Query using the Entity Manager and criteria Query
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultSet = query.getResultList();
		
		logger.info("CriteriaAPI...Like clause -->"+resultSet);
		

	}
	
	@Test	 
	public void criteriaQuery_coursesWithoutStudents() {
		
		//1.use criteriaBuilder to create a Criteria query returning expected result set
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		//2.Define roots for tables involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3.Define Predicate etc using Criteria builder
		Predicate courseNoStudents = cb.isEmpty(courseRoot.get("students"));
		//4.Add predicates etc to the Criteria Query
		cq.where(courseNoStudents);
		
		//5. Build the Typed Query using the Entity Manager and criteria Query
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultSet = query.getResultList();
		
		logger.info("CriteriaAPI...Course with no students -->"+resultSet);
		

	}
	
	@Test	 
	public void criteriaQuery_join() {
		
		//1.use criteriaBuilder to create a Criteria query returning expected result set
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		//2.Define roots for tables involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3.Define Predicate etc using Criteria builder
		Join<Object, Object> joinCourseStudent = courseRoot.join("students");
		//4.Add predicates etc to the Criteria Query
		
		
		//5. Build the Typed Query using the Entity Manager and criteria Query
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultSet = query.getResultList();
		
		logger.info("CriteriaAPI...Course JOIN with students -->"+resultSet);
		

	}
	@Test	 
	public void criteriaQuery_leftJoin() {
		
		//1.use criteriaBuilder to create a Criteria query returning expected result set
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		//2.Define roots for tables involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3.Define Predicate etc using Criteria builder
		Join<Object, Object> joinCourseStudent = courseRoot.join("students",JoinType.LEFT);
		//4.Add predicates etc to the Criteria Query
		
		
		//5. Build the Typed Query using the Entity Manager and criteria Query
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultSet = query.getResultList();
		
		logger.info("CriteriaAPI...Course LEFT JOIN with students -->"+resultSet);
		

	}


	

}
