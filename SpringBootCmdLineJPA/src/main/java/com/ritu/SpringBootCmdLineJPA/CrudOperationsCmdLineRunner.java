package com.ritu.SpringBootCmdLineJPA;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ritu.SpringBootCmdLineJPA.model.Book;
import com.ritu.SpringBootCmdLineJPA.repository.BookRepository;

//@Order(value=2)
@Component
public class CrudOperationsCmdLineRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CrudOperationsCmdLineRunner.class);
	@Autowired
	BookRepository repository;
	@Override
	public void run(String... args) throws Exception {
		// Calling various crud operations
		logger.info("Calling CRUD Operations...");
		logger.info("List all books");
		Iterable<Book> books = repository.findAll();
		Iterator booksIterator   = books.iterator();
		while(booksIterator.hasNext()) {
			logger.info("------------"+booksIterator.next().toString()+"--------------");
		}
		

        logger.info("Find a book with specific id");
        repository.findById(2l).ifPresent(x -> logger.info(x.toString()));

        logger.info("Find  a book with name ('React')");
        repository.findByName("React").forEach(x -> logger.info(x.toString()));
        
        logger.info("Find  a book with name ('Java')");
        repository.findByNameContaining("Java").forEach(x -> logger.info(x.toString()));

		
	}
	
	

}
