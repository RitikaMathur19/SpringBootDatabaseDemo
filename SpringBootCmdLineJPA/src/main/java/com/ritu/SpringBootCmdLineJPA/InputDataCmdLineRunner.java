package com.ritu.SpringBootCmdLineJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ritu.SpringBootCmdLineJPA.model.Book;
import com.ritu.SpringBootCmdLineJPA.repository.BookRepository;

@Order(value=1)
@Component
public class InputDataCmdLineRunner implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(InputDataCmdLineRunner.class);  
	@Autowired
	BookRepository bookRepository;

	@Override
	public void run(String... args) throws Exception {
		// Insert Book Records in the Database
		logger.info("Inserting records to the database started...");
		bookRepository.save(new Book("Java"));
		bookRepository.save(new Book("Node JS"));
		bookRepository.save(new Book("Java Spring"));
		bookRepository.save(new Book("Java Persistence"));
		bookRepository.save(new Book("Cloud"));
		bookRepository.save(new Book("React"));
	}

}
