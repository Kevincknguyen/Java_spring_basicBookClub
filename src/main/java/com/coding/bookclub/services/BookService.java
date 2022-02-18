package com.coding.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coding.bookclub.models.Book;
import com.coding.bookclub.repositories.BookRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepo;
	public BookService(BookRepository bookRepo) {
		this.bookRepo=bookRepo;
	}
	
	
	
//	READ ALL
	public List<Book> allBooks(){
		return bookRepo.findAll();
	}
	
//	CREATE ONE
	public Book createBook(Book book) {
		System.out.println("Service:Attempting to create book");
		return bookRepo.save(book);
	}
	
//	READ ONE BY ID
	public Book findSpecificBook(Long id) {
		Optional<Book> isBook=bookRepo.findById(id);
		if(isBook.isPresent()) {
			System.out.println("Service:Retreiving book");
			return isBook.get();
		}
		else return null;
	}
	
//	READ ALL BY BORROWED ID
	public List<Book> findAllBorrowedBook(Long id) {
		List<Book> isBooks=bookRepo.findAllByBorrow_id(id);
		if(!isBooks.isEmpty()) {
			return isBooks;
		}
		else return null;
	}
	
//	READ ALL WHERE I OWN THE BOOKS
	public List<Book> findAllMyBook(Long id) {
		List<Book> isBooks=bookRepo.findAllByUser_id(id);
		if(isBooks.isEmpty()==false) {
			return isBooks;
		}
		else return null;
	}
	
//	UPDATE ONE BY ID
	public Book updateBook(Book book) {
		Optional<Book> isBook=bookRepo.findById(book.getId());
		if(isBook.isPresent()) {
			
			Book newBook=isBook.get();
			
			newBook.setAuthor(book.getAuthor());
			newBook.setTitle(book.getTitle());
			newBook.setThoughts(book.getThoughts());
			
			bookRepo.save(newBook);
			return newBook;
		}
		else return null;
	}
	
	

//	UPDATE BORROWER ID
	public Book borrowBook(Book book) {
		Optional<Book> isBook=bookRepo.findById(book.getId());
		if(isBook.isPresent()) {
			
			Book newBook=isBook.get();
			newBook.setBorrow(book.getBorrow());
			bookRepo.save(newBook);
			return newBook;
			
			
		}
		else return null;
	}
	
//	DELETE BOOK BY ITS ID
	public String deleteBook(Long id) {
	     Optional<Book> optionalBook = bookRepo.findById(id);
	     if(optionalBook.isPresent()) {
	    	 bookRepo.deleteById(id);
	         return "Completed delete";
	         
	     } 
	     else {
	    	 return "No expense with ID found";
	     }
	 	}
	
	
	
	
}
