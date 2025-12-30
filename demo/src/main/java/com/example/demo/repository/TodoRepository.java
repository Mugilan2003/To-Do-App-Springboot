package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Todomodel;


public interface TodoRepository extends JpaRepository<Todomodel, Long> {
	

}
