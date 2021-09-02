package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ToDo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String description;
	
	@NotNull
	private boolean completed;
	
	@NotNull
	private Date dueDate;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	
	public ToDo() {
		this(-1, "N/A", false, new Date(), null);
	}

	public ToDo(Integer id, @NotNull String description, @NotNull boolean completed, @NotNull Date date, User user) {
		super();
		this.id = id;
		this.description = description;
		this.completed = completed;
		this.dueDate = date;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Date getDate() {
		return dueDate;
	}

	public void setDate(Date date) {
		this.dueDate = date;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() { 
		return this.user;
	}
	public static long getSerialversionid() { 
		return serialVersionUID;
	}
	

	
	
	
}
