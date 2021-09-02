package com.cognixia.jump.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public User updateUser(User user) { 
		if(userRepo.existsById(user.getId())) {
			User updated = userRepo.save(user);
			return updated;
		}
		return new User();
	}
	
	
}
