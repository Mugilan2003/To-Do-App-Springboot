package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="UserTable")
public class Users {
	@Id
	@GeneratedValue
	Long id;
	@Email
	String email;
	String password;
	public Users() {
		
	}
	public Users(String email, String password){
		this.email=email;
		this.password=password;
	}
	public String getPassword() {
		return password;
	}

}
