package com.example.demo.controller;
//create a API

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {
	@GetMapping("/hello")
	String sayHello() {
		return "Hello Spring";
	}
}
