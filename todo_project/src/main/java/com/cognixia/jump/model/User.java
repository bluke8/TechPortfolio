package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String name;
	
	@NotBlank(message="User must have a username")
	private String username;
	
	@NotNull
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ToDo> tasks;
	
	public User() { 
		this(-1, "No Name", "N/A", "password", new ArrayList<ToDo>());
	}
	
	public User(Integer id, String name, String username, String password,
			List<ToDo> tasks) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.tasks = tasks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ToDo getTask(Integer id) {
		for (int i=0; i<tasks.size(); i++) {
			if(tasks.get(i).getId() == id) { 
				return tasks.get(i);
			}
		}
		return new ToDo();
	}
	
	//tid = task id
	public void setTasks(List<ToDo> tasks) {
		
		
		for(int tid =0; tid < tasks.size(); tid++) { 
			addTask(tasks.get(tid));
		}
	}
	
	public void addTask(ToDo task) { 
		task.setUser(this);
		tasks.add(task);
	}

	
	
	
	
}
