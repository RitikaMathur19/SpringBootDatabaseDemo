package com.ritu.SpringBootCmdLineJPA.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ritu.SpringBootCmdLineJPA.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findByName(String name);
	List<Book> findByNameContaining(String name);

}
