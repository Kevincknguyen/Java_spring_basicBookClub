package com.coding.bookclub.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull (message="Please provide a title")
	 @Size(min = 3, max = 200, message="Please provide valid title between 3-200 characters")
	 private String title;
	
	@NotNull (message="Please provide an author")
	 @Size(min = 3, max = 200, message="Please provide valid author between 3-200 characters")
	 private String author;
	
	@NotNull (message="Must not be blank")
	@Size(min = 3, max = 200, message="Please provide valid thoughts between 3-200 characters")
    private String thoughts;
	
	@Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="borrow_id")
    private User borrow;
    
    
    public Book() {
    	
    }


    
    
    
    
    
    
    @PrePersist
    protected void onCreate() {
    	this.createdAt=new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
    	this.updatedAt=new Date();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

	public User getBorrow() {
		return borrow;
	}


	public void setBorrow(User borrow) {
		this.borrow = borrow;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public String getThoughts() {
		return thoughts;
	}



	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
    
    
    
    
    
    
    
    
    
}