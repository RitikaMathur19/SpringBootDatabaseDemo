package com.ritu.hibernate.jpa.app;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.ritu.hibernate.jpa.app.entity.Course;
import com.ritu.hibernate.jpa.app.entity.Student;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateAdvancedConceptsDemoApplication.class)
class JPQLTests {

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void jpql_basic() {
		Query query = em.createNamedQuery("query_get_all_courses");
		List resultlist = query.getResultList();
		logger.info("JPQL Select c from Course c...{}"+resultlist);
		
	}
	@Test
	//typed queries are better to use - expecting results of a particular type - makes it easy to understand
	public void jpql_typed() {
		//Hardcoded query
		TypedQuery<Course> typedHardcodedQuery = em.createQuery("Select c from Course c",Course.class);
		//expecting a list of results of 'Course' type in a named query
		TypedQuery<Course> typedQuery = em.createNamedQuery("query_get_all_courses",Course.class);
		List<Course> resultlist = typedHardcodedQuery.getResultList();
		List<Course> resultlistNamed = typedQuery.getResultList();
		logger.info("JPQLTyped Hardcoded query[Select c from Course c]...{}"+resultlist);
		logger.info("JPQLTyped Named Query ->query_get_all_courses...{}"+resultlistNamed);
		
	}
	@Test
	public void jpql_where() {
		TypedQuery<Course> typedQuery = em.createNamedQuery("query_get_angular_courses",Course.class);
		List<Course> resultlist = typedQuery.getResultList();
		logger.info("JPQLSelect c from Course c where name like '%Angular%' ...{}"+resultlist);
		
	}

	@Test
	public void jpql_courses_without_students() {
		 TypedQuery<Course> course_no_students = em.createQuery("Select c from Course c where c.students is empty ",Course.class);
		List<Course> resultlist = course_no_students.getResultList();
		logger.info("Courses without students{}"+resultlist);
		
	}
	
	@Test
	public void jpql_courses_withAtLeast2_students() {
		 TypedQuery<Course> course_atleast2_students = em.createQuery("Select c from Course c where size(c.students) >=2 ",Course.class);
		List<Course> resultlist = course_atleast2_students.getResultList();
		logger.info("Courses with atleast 2 students{}"+resultlist);
		
	}
	@Test
	public void jpql_courses_orderedBy_students() {
		 TypedQuery<Course> orderBY_students = em.createQuery("Select c from Course c order by size(c.students) desc",Course.class);
		List<Course> resultlist = orderBY_students.getResultList();
		logger.info("Courses orderBY_students{}"+resultlist);
		
	}
	
	@Test
	public void jpql_students_with_passport_pattern() {
		 TypedQuery<Student> studentsPassportPattern = em.createQuery("Select s from Student s where s.passport.number like '%656%' ",Student.class);
		List<Student> resultlist = studentsPassportPattern.getResultList();
		logger.info("Students with specific passport pattern{}"+resultlist);
		
	}
	
	@Test
	public void jpql_students_join_courses() {
		 Query join = em.createQuery("Select c , s from Course c JOIN c.students s");
		 //JPQL returns array of arrays first array of course , second one of student
		List<Object[]> resultlist = join.getResultList();
		logger.info("Result size-->"+resultlist.size());
		for(Object[] result:resultlist) {
			logger.info(" Course{}  Student{} "+result[0]+result[1]);
			
		}
		
		
	}
	
	@Test
	public void jpql_students_Leftjoin_courses() {
		 Query join = em.createQuery("Select c , s from Course c LEFT JOIN c.students s");
		 //JPQL returns array of arrays first array of course , second one of student
		List<Object[]> resultlist = join.getResultList();
		logger.info("Result size-->"+resultlist.size());
		for(Object[] result:resultlist) {
			//observation : curly braces matter in the output
			logger.info(" results---> Students {} Courses{} "+result[0] , result[1]);
			
		}
		
		
	}
	
	@Test
	public void jpql_students_crossjoin_courses() {
		 Query join = em.createQuery("Select c , s from Course c , Student s");
		 //JPQL returns array of arrays first array of course , second one of student
		List<Object[]> resultlist = join.getResultList();
		logger.info("Result size-->"+resultlist.size());
		for(Object[] result:resultlist) {
			logger.info(" Students{}  Course{} "+result[0] , result[1]);
			
		}
		
		
	}
	}
