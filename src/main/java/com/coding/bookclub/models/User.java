package com.coding.bookclub.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="users")
public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotEmpty(message="name is required!")
		@Size(min = 3, max = 200, message="Please provide valid name between 3-200 characters")
		private String name;
		
		@NotEmpty
		@Size(min = 8, max = 200, message="Please provide valid password between 8-200 characters")
		private String password;
		
		
		@NotEmpty(message="Email is required")
		@Email(message="Please provide a valid email")
		private String email;
		
		@NotNull
		@Min(value = 3, message="Please provide valid name between 3-200 characters")
		private Integer pin;
		
		
		@Transient
		@NotEmpty(message="Confirm password is required")
		@Size(min = 8, max = 200, message="Please provide valid password between 8-200 characters")
		private String confirm;
		
		 @Column(updatable=false)
		    @DateTimeFormat(pattern="yyyy-MM-dd")
		    private Date createdAt;
		    @DateTimeFormat(pattern="yyyy-MM-dd")
		    private Date updatedAt;
		    
		    
		 @OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade=CascadeType.ALL) 
		 private List<Book> books;
		 
		 @OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade=CascadeType.ALL) 
		 private List<Book> borrowBooks;
	    
	    public User() {
	    	
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getPin() {
			return pin;
		}

		public void setPin(Integer pin) {
			this.pin = pin;
		}

		public String getConfirm() {
			return confirm;
		}

		public void setConfirm(String confirm) {
			this.confirm = confirm;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}

		public List<Book> getBooks() {
			return books;
		}

		public void setBooks(List<Book> books) {
			this.books = books;
		}
	    
	    
		 @PrePersist
		    protected void onCreate() {
		    	this.createdAt=new Date();
		    }
		    
	    @PreUpdate
	    protected void onUpdate() {
	    	this.updatedAt=new Date();
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

}
