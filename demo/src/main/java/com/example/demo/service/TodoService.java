package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.model.Todomodel;
import com.example.demo.repository.TodoRepository;

@Service // or @Bean it must be an bean class for autowire
public class TodoService {
	//Autowire
	@Autowired
	private TodoRepository todoRepository;
	
	public Todomodel getTodo(Long id) {
		return todoRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Todo not found!"));
	}
	
	public Todomodel createTodo(Todomodel todo) {
		return todoRepository.save(todo); //save -> create a data
	}
	
	public List<Todomodel> getAlltodos(){
		return todoRepository.findAll();
	}
	public Page<Todomodel> getTodopage(int pageNumbr,int size){
		PageRequest pageable=PageRequest.of(pageNumbr, size);
		return todoRepository.findAll(pageable);
	}
	public Todomodel updateTodo(Todomodel todo) {
		System.out.println(todo);
		Todomodel existingTodo = todoRepository.findById(todo.getId())
	            .orElseThrow(() -> new RuntimeException("Todo not found"));

	    existingTodo.setTitle(todo.getTitle());
	    existingTodo.setDescription(todo.getDescription());
	    existingTodo.setCompleted(todo.isCompleted());

	    return todoRepository.save(existingTodo);
//		return todoRepository.save(todo);
	}
	
	public void deleteTodoById(Long id) {
		todoRepository.delete(getTodo(id));
	}
}
