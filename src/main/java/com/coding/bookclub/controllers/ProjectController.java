package com.coding.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public String index(HttpSession session,Model model) {
		if(session.getAttribute("currentUser")==null) {
			return "redirect:/";
		}
		model.addAttribute("currentUser",session.getAttribute("currentUser"));
		List<Book> books=bookService.allBooks();
		model.addAttribute("books",books);
		return "Index.jsp";
	}
	
	
//	NEW BOOK PAGE
	@GetMapping("/addnewbook")
	public String addnewbookpage(
			@ModelAttribute("book") Book book,
			HttpSession session,
			Model model) {
		
		
//		model.addAttribute("currentUser",session.getAttribute("currentUser"));
		
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
		model.addAttribute("currentUser",session.getAttribute("currentUser"));
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
		System.out.println("Specific"+specific.getUser().getId());
		System.out.println("Current user"+session.getAttribute("currentID"));
		
		
		
		
		
		if (specific!=null) {
			if(specific.getUser().getId() !=session.getAttribute("currentID")) {
				return "redirect:/index";
			}
			model.addAttribute("book",specific);
			return "EditBook.jsp";
		}
		else return "redirect:/index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	CALLS
//	REGISTER
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser")User newUser,
			BindingResult result, 
			Model model,
			HttpSession session) {
		
		User verified= userService.register(newUser,result);
		
		if (result.hasErrors()) {
			model.addAttribute("newLogin",new LoginUser());
			return "LoginRegister.jsp";
		}
		
		session.setAttribute("currentUser", verified);
		session.setAttribute("currentID", verified.getId());
		System.out.println(session.getAttribute("currentUser"));
		return "redirect:/index";
	}
	
//	LOGIN
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("newLogin")LoginUser newLogin,
			BindingResult result, 
			Model model,
			HttpSession session) {
		
		
		if (result.hasErrors()) {
			model.addAttribute("newUser",new User());
			return "LoginRegister.jsp";
		}
		else {
			
		User verified=userService.login(newLogin,result);
		if (verified==null) {
			model.addAttribute("newUser",new User());
			return "LoginRegister.jsp";
		}
		
		session.setAttribute("currentUser", verified);
		session.setAttribute("currentID", verified.getId());
		return "redirect:/index";
		}
	}
	
//	LOGOUT
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("currentUser");
		session.removeAttribute("currentID");
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
		bookService.createBook(book);
		System.out.println("Creating book...");
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
	
	
}
