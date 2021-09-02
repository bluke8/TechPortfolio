package com.cognixia.jump.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.ToDo;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.ToDoRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.ToDoService;

@RestController
@RequestMapping("/api")
public class ToDoController {

	@Autowired
	ToDoRepository repo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ToDoService todoService;
	
	@GetMapping("/todo")
	public List<ToDo> getToDoList() { 
		return repo.findAll();
	}
	
	@PostMapping("/todo")
	public ResponseEntity<?> createTask(@RequestBody ToDo task) { 
		task.setId(-1);
		ToDo created = repo.save(task);
		return ResponseEntity.status(201).body(created);
		
	}
	
	@GetMapping("/todo/{id}")
	public List<ToDo> getToDoByUserId(@PathVariable Integer id) throws ResourceNotFoundException {
		List<ToDo> usersTasks = new ArrayList<ToDo>();
		List<ToDo> allTasks = repo.findAll();
		if(repo.existsById(id)) { 
			for(ToDo task : allTasks) {
				if(task.getUser().getId() == id) {
					usersTasks.add(task);
				}
			}
			return usersTasks;
		}
		throw new ResourceNotFoundException("Task with id: "+id+" wasn't found");
		
	}
	
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<?> deleteToDo(@PathVariable Integer id) { 
		ToDo task = todoService.deleteToDoById(id);
		
		if(task.getId() == -1)  {
			return ResponseEntity.status(404).body("Task wasn't found with id: "+id);
		}
		return ResponseEntity.status(200).body("Task was deleted with id: "+id);
	}
	
//	@DeleteMapping("/todo/{id}")
//	public ResponseEntity<?> deleteByUser(@PathVariable Integer id) throws ResourceNotFoundException { 
//		if(userRepo.existsById(id)) { 
//			User user = userRepo.getById(id);
//			
//			repo.DeleteByUser(user);
//			
//			return ResponseEntity.status(200).body("Tasks were deleted for user with id: "+id);
//		}
//		throw new ResourceNotFoundException("User wasn't found with id: "+id);
//	}
	
	// Doesn't work, "Cannot convert String to Date" when trying to utilize this in postman
	@PutMapping("/todo/update_due_date")
	public ResponseEntity<?> updateTaskDueDate(@PathParam(value = "id") Integer id, @PathParam(value = "date") Date date){
		ToDo updated = todoService.updateTaskDueDate(id, date);
		
		if(updated.getId() == -1) {
			return ResponseEntity.status(404).body("Task with id: "+id+" not found");
		}
		return ResponseEntity.status(200).body(updated);
		
	}
	
	//can update any info on any ToDo
	@PutMapping("/todo")
	public ResponseEntity<?> updateTask(@RequestBody ToDo task){
		ToDo updated = todoService.updateTask(task);
		return ResponseEntity.status(200).body(updated);
	}
//	@GetMapping("/todo/incomplete")
//	public List<ToDo> incompleteList() { 
//		return repo.incompleteToDos();
//	}
	
	
	
	
	
}
