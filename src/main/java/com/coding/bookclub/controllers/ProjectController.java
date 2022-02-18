package com.coding.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coding.bookclub.models.Book;
import com.coding.bookclub.models.LoginUser;
import com.coding.bookclub.models.User;
import com.coding.bookclub.services.BookService;
import com.coding.bookclub.services.UserService;

@Controller
public class ProjectController {
	
	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;

//	VIEWS
//	LOGIN AND REGISTER PAGE
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "LoginRegister.jsp";
	}
	
//	ACCOUNT HOME
	@GetMapping("/index")
	public String index(HttpSession session,Model model,@ModelAttribute("book") Book book) {
		if(session.getAttribute("currentUser")==null) {
			return "redirect:/";
		}
		
		List<Book> allBooks = bookService.findAllBorrowedBook(null);
		List<Book> borrowedBooks = bookService.findAllBorrowedBook((Long) session.getAttribute("currentID"));
		List<Book> myBooks = bookService.findAllMyBook((Long) session.getAttribute("currentID"));
		
		model.addAttribute("books",allBooks);
		model.addAttribute("borrow",borrowedBooks);
		model.addAttribute("myBooks",myBooks);
		model.addAttribute("null",null);
		return "Index.jsp";
	}
	
	
//	NEW BOOK PAGE
	@GetMapping("/addnewbook")
	public String addnewbookpage(
			HttpSession session, @ModelAttribute("book") Book book) {
		
		if(session.getAttribute("currentUser")==null) {
			return "redirect:/";
		}
		else {
		}
		return "AddBook.jsp";
	}
	

//	SHOW ONE PAGE
	
	@GetMapping("/showbook/{id}")
	public String showPage(Model model, HttpSession session,@PathVariable("id") Long id) {
		Book specific=bookService.findSpecificBook(id);
		
		if (specific!=null) {
			model.addAttribute("book",specific);
			return "ShowBook.jsp";
		}
		else return "redirect:/index";
	}
	
//	EDIT BOOK PAGE
	
	@GetMapping("/editbook/{id}")
	public String shoeEditPage(Model model, HttpSession session,@PathVariable("id") Long id) {
		Book specific=bookService.findSpecificBook(id);
		
		if (specific!=null) {
			if(specific.getUser().getId() !=session.getAttribute("currentID")) {
				return "redirect:/index";
			}
			model.addAttribute("book",specific);
			return "EditBook.jsp";
		}
		else {return "redirect:/index";}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	CALLS
//	REGISTER
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser")User newUser,
			BindingResult result, 
			Model model,
			HttpSession session) {
		
		
		if (result.hasErrors()) {
			model.addAttribute("newLogin",new LoginUser());
			return "LoginRegister.jsp";
		}
		
		User verifiedUser= userService.register(newUser,result);
		if (verifiedUser==null) {
			model.addAttribute("newLogin",new LoginUser());
			return "LoginRegister.jsp";
		}
		session.setAttribute("currentUser", verifiedUser);
		session.setAttribute("currentID", verifiedUser.getId());
		System.out.println("Registering account");
		return "redirect:/index";
	}
	
//	LOGIN
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("newLogin")LoginUser newLogin,
			BindingResult result, 
			Model model,
			HttpSession session) {
		
			
		User verifiedUser=userService.login(newLogin,result);
		
		if (verifiedUser==null || result.hasErrors()) {
			model.addAttribute("newUser",new User());
			return "LoginRegister.jsp";
		
		}
		session.setAttribute("currentUser", verifiedUser);
		session.setAttribute("currentID", verifiedUser.getId());
		System.out.println("Logging in");
		return "redirect:/index";
	}
	
//	LOGOUT
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		
	}
	
//	NEW BOOK
	@PostMapping("/api/newBook")
	public String newBook(
			@Valid @ModelAttribute("book") Book book,
			BindingResult result,
			HttpSession session,
			Model model) {
		
		if (result.hasErrors()) {
			return "AddBook.jsp";
		}
//		book.setUser( (User) session.getAttribute("currentUser"));
		bookService.createBook(book);
		return"redirect:/index";
		
	}
	
//	EDIT BOOK
	@RequestMapping(value="/api/edit/{id}", method=RequestMethod.PUT)
	public String editBookAction(
			@PathVariable ("id")Long id,
			@Valid @ModelAttribute("book")Book book,
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "EditBook.jsp";
		} else {
			bookService.updateBook(book);
			return "redirect:/index";
		}
	}
	
	
	
//	BORROW BOOK
	@RequestMapping(value="/api/borrow/{id}", method=RequestMethod.PUT)
	public String borrowBook(@PathVariable ("id")Long id, 
			HttpSession session,
			@ModelAttribute("book") Book book) {
		bookService.borrowBook(book);
		return "redirect:/index";
		
	}
	
	
//	DELETE BOOK
	@DeleteMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable("id")Long id) {
		bookService.deleteBook(id);
		return "redirect:/index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
