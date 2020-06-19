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

	}
