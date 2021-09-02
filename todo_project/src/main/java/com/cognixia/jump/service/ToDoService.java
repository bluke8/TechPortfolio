package com.cognixia.jump.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.ToDo;
import com.cognixia.jump.repository.ToDoRepository;

@Service
public class ToDoService {
	
	@Autowired
	ToDoRepository toDoRepo;
	
	public ToDo deleteToDoById(Integer id) { 
		ToDo deleted = new ToDo();
		
		if(toDoRepo.existsById(id)) { 
			deleted = toDoRepo.getById(id);
			toDoRepo.deleteById(id);
		}
		return deleted;
	}
	
	
	public ToDo updateTaskDueDate(Integer id, Date date) { 
		
		Optional<ToDo> found = toDoRepo.findById(id);
		if(found.isPresent()) { 
			ToDo toUpdate = found.get();
			toUpdate.setDate(date);
			ToDo updated = toDoRepo.save(toUpdate);
			return updated;
		}
		return new ToDo();
		
	}
	
	public ToDo updateTask(ToDo task) {
		if(toDoRepo.existsById(task.getId())) {
			ToDo updated = toDoRepo.save(task);
			return updated;
		}
		return new ToDo();
	}
	
}
