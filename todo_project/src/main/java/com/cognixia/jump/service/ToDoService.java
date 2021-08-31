package com.cognixia.jump.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.ToDo;
import com.cognixia.jump.repository.ToDoRepository;

@Service
public class ToDoService {
	
	@Autowired
	ToDoRepository ToDoRepo;
	
	public ToDo deleteToDoById(Integer id) { 
		ToDo deleted = new ToDo();
		
		if(ToDoRepo.existsById(id)) { 
			deleted = ToDoRepo.getById(id);
			ToDoRepo.deleteById(id);
		}
		return deleted;
	}
	
	public ToDo updateTaskStatusById(Integer id) { 
		if(ToDoRepo.existsById(id)) { 
			ToDo updated = ToDoRepo.save(ToDoRepo.getById(id));
			return updated;
		}
		return new ToDo();
	}
}
