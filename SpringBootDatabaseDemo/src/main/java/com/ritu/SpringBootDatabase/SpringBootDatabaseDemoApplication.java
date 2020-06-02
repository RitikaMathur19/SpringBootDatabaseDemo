package com.ritu.SpringBootDatabase;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ritu.SpringBootDatabase.entity.Person;
import com.ritu.SpringBootDatabase.jdbcDAO.PersonJdbcDao;

@SpringBootApplication
public class SpringBootDatabaseDemoApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(SpringBootDatabaseDemoApplication.class);
	@Autowired
	PersonJdbcDao dao;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Just logging,,,");
		// Spring JDBC functions
		logger.info("All users -> "+dao.findAll());
		logger.info("Find user -> "+ dao.findById(10001));
		logger.info("Find user by name -> "+ dao.findByName("Badam"));
		logger.info("Counting persons -> "+dao.count());
		logger.info("Delete user -> "+ dao.deleteById(10001));
		logger.info("Delete user in location -> "+ dao.deleteByLocationName("Pune", "Kaju"));
		logger.info("Inserting data .. "+dao.insert(new Person(10007,"Pista","MP",new Date())));
		logger.info("Updating data .. "+dao.update(new Person(10005,"Khubani","Kerala",new Date()))); 
		
		
	}

}
