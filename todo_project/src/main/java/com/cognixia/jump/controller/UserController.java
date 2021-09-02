package com.cognixia.jump.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	UserService service;
	
	@GetMapping("/user")
	public List<User> getUsers() { 
		return repo.findAll();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable Integer id) throws ResourceNotFoundException{ 
		if(repo.existsById(id)) { 
			User found = repo.getById(id);
			return ResponseEntity.status(200).body(found);
		}
		throw new ResourceNotFoundException("User with id: "+id+" wasn't found");
	}
	
//	@GetMapping("/user/{username}")
//	public User getUserByUsername(@PathVariable String username){
//		return repo.findByUsername(username);
//	}
	
	@PostMapping("/user")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user) { 
		user.setId(-1);
		User created = repo.save(user);
		return ResponseEntity.status(201).body(created);
	}
	
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		User updated = service.updateUser(user);
		
		if(updated.getId() == -1) {
			return ResponseEntity.status(404).body("User with id: "+user.getId()+" wasn't found");
		}
		return ResponseEntity.status(200).body(updated);
	}
}
