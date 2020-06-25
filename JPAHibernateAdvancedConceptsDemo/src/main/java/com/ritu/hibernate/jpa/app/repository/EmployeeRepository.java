package com.ritu.hibernate.jpa.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ritu.hibernate.jpa.app.entity.Employee;
import com.ritu.hibernate.jpa.app.entity.FullTimeEmployee;
import com.ritu.hibernate.jpa.app.entity.PartTimeEmployee;



@Repository
@Transactional
public class EmployeeRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;

	public List<FullTimeEmployee> retrieveFullTimeEmployees() {
		logger.info("Selecting Full Time employees-->");
		return em.createQuery("Select e from FullTimeEmployee e ", FullTimeEmployee.class).getResultList();
	}

	public List<PartTimeEmployee> retrievePartTimeEmployees() {
		logger.info("Selecting Part Time employees-->");
		return em.createQuery("Select e from PartTimeEmployee e ", PartTimeEmployee.class).getResultList();
	}

	public void insertEmployee(Employee emp) {
		logger.info("Inserting into employees-->");
		em.persist(emp);

	}

}
