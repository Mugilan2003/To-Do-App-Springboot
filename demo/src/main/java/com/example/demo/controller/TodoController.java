package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Todomodel;
import com.example.demo.service.TodoService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/v1/todo") //grouping by name
public class TodoController {
	@Autowired
	private TodoService todoService; //create instance automatically
	
	//Request Param ex.todo?id=34 & id=50 key-->id value-->34
	@GetMapping("")
	ResponseEntity<List<Todomodel>> getTodos() {
		try {
			List<Todomodel> listTodos= todoService.getAlltodos();
			return new ResponseEntity<>(listTodos,HttpStatus.OK);
		}
		catch(RuntimeException e) {
//			log.error("Exception",e);
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Path Variable enter 1 return Todo with Id 1
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",description="Todo Received Successfully"),
			@ApiResponse(responseCode="404",description="Todo was not found!")
	})
	@GetMapping("/{id}")
	ResponseEntity<Todomodel> getTodoById(@PathVariable Long id) {
		try{
			Todomodel createdTodo=todoService.getTodo(id);
			return new ResponseEntity<>(createdTodo,HttpStatus.OK);
		}
		catch(RuntimeException e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/page")
	ResponseEntity<Page<Todomodel>> getTodopaged(@RequestParam int pageNumbr, @RequestParam int size){
		return new ResponseEntity<>(todoService.getTodopage(pageNumbr, size),HttpStatus.OK);
	}
	//giving input or create a request with PostMap
	@PostMapping("/create")
	ResponseEntity<Todomodel> createUser(@RequestBody Todomodel todo) {
		return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
	}
	
	//put method
	@PutMapping("/{id}")
	ResponseEntity<Todomodel> updateTodoById(@PathVariable Long id,@RequestBody Todomodel todo) {
		todo.setId(id);
		return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.OK);
	}
	
	//delete method
	@DeleteMapping("/{id}")
	void deleteTodoById(@PathVariable long id) {
		todoService.deleteTodoById(id);
	}
	
}
//@GetMapping("/get")
//String getTodo() {
////	todoService.printTodos();//instance created automatically
//	return "Todo";
//}
