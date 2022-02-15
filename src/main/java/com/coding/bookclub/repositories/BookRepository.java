package com.coding.bookclub.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.bookclub.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
	List<Book> findAll();
	List<Book> findByUser_id(Long id);

}
