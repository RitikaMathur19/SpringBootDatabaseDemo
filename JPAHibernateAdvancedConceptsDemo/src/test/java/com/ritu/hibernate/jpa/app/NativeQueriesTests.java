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
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaHibernateAdvancedConceptsDemoApplication.class)
class NativeQueriesTests {

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void nativeQueries_basic() {
		Query query = em.createNativeQuery("Select * from course",Course.class);
		List resultlist = query.getResultList();
		logger.info("Native Query Select * from Course ...{}"+resultlist);
		
	}
	
	
	@Test
	public void nativeQueries_with_parameter() {
		Query query = em.createNativeQuery("Select * from course where id =?",Course.class);
		query.setParameter(1, 1001L);
		List resultlist = query.getResultList();
		logger.info("Native Query with param...{}"+resultlist);
		
	}
	@Test
	public void nativeQueries_with_named_parameter() {
		Query query = em.createNativeQuery("Select * from course where id =:id",Course.class);
		//instead of positional parameters , we are using named parameters
		query.setParameter("id", 1002L);
		List resultlist = query.getResultList();
		logger.info("Native Query with named param ...{}"+resultlist);
		
	}
	@Test
	@Transactional
	public void nativeQueries_with_updates() {
		Query query = em.createNativeQuery("UPDATE COURSE SET last_updated_date=sysdate()",Course.class);
		
		int noofRowsUpdated= query.executeUpdate();                                                                                                                                                                                                                                                                                             ;
		logger.info("noofRowsUpdated->{}"+noofRowsUpdated);
		
	}
	}
