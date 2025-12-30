package com.example.demo.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserService userService;	
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body) {
		String email=body.get("email");
		String password=passwordEncoder.encode(body.get("password"));
		if(userRepository.findByEmail(email).isPresent()) {
			return new ResponseEntity<>("Email already exist",HttpStatus.CONFLICT);
		}
		Users user=new Users(email,password);
		userService.createUser(user);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body) {
		String email=body.get("email");
		String password=body.get("password");
		
		var userOptional=userRepository.findByEmail(email);
		System.out.println("UserOptional : "+ userOptional.get().getPassword());
		System.out.println("Password: "+  password);
		if(userOptional.isEmpty()) {
			return new ResponseEntity<>("User not registered",HttpStatus.UNAUTHORIZED);
		}
		var user=userOptional.get();
		if(!passwordEncoder.matches(password, user.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
		}
		String token=jwtUtil.generateToken(email);
		System.out.println("token : "+token);
		return ResponseEntity.ok(Map.of("token", token));
	}
}
