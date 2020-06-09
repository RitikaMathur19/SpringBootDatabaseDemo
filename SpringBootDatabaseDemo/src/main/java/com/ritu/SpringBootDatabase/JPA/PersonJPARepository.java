package com.ritu.SpringBootDatabase.JPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ritu.SpringBootDatabase.entity.Person;


@Repository
@Transactional
public class PersonJPARepository {

	// connect to Database
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Person> findAllPersons() {

		TypedQuery<Person> allPersons= entityManager.createNamedQuery("find_all_persons",Person.class);
		return allPersons.getResultList();

	}

	public Person findById(int id) {
		return entityManager.find(Person.class, id);
	}
	

	// update and insert use "merge" function as far as Entitymanager is concerned
	// so two functions are not required as such
	public Person update(Person person) {

		return entityManager.merge(person);
	}

	public Person insert(Person person) {

		return entityManager.merge(person);
	}

	public void deleteById(int id) {
		Person person = findById(id);
		entityManager.remove(person);

	}

}
