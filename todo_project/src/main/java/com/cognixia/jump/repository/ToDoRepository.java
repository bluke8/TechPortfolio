package com.cognixia.jump.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.ToDo;
import com.cognixia.jump.model.User;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer>{
	
//	@Query("select * from to_do where completed = 0")
//	List<ToDo> incompleteToDos();
	
//	@Transactional
//	void DeleteByUser(User user);
}
