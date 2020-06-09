package com.ritu.SpringBootDatabase;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import com.ritu.SpringBootDatabase.JPA.PersonJPARepository;
import com.ritu.SpringBootDatabase.entity.Person;
import com.ritu.SpringBootDatabase.jdbcDAO.PersonJdbcDao;

@SpringBootApplication
public class SpringJPADemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(SpringJPADemoApplication.class);
	@Autowired
	PersonJPARepository personRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringJPADemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Just logging,,,");
		// Spring JPA functions
		
		logger.info("Find user -> " + personRepo.findById(10001));
		logger.info("Inserting data .. " + personRepo.insert(new Person("Pista", "MP", new Date())));
		logger.info("Inserting data .. " + personRepo.insert(new Person("Kaju", "Delhi", new Date())));
		logger.info("Inserting data .. " + personRepo.insert(new Person("Pista", "Bombay", new Date())));
		logger.info("Updating data .. " + personRepo.update(new Person(1, "Khubani", "Kerala", new Date())));
		logger.info("All users -> "+personRepo.findAllPersons());
		logger.info("Delete user -> ");
		personRepo. deleteById(1);
		logger.info("All users after updates-> "+personRepo.findAllPersons());
		
	}

}
