package com.ritu.SpringBootDatabase.JPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ritu.SpringBootDatabase.entity.Person;

@Repository
@Transactional
public class PersonJPARepository {
	
	//connect to Database
	@PersistenceContext
	EntityManager entityManager;
	
	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}

}
