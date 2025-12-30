package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

		@Autowired
		private UserRepository userRepository;
		
		public Users createUser(Users user) {
			return userRepository.save(user); //save -> create a data
		}
		
		public Users getTodoByEmail(long id) {
			return userRepository.findById(id)
					.orElseThrow(()->new RuntimeException("Todo not found!"));
		}
		
		
}
