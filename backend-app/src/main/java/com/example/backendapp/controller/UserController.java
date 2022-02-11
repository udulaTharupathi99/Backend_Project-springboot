package com.example.backendapp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.backendapp.model.User;
import com.example.backendapp.repository.UserRepository;


@RestController
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRep;
	
	//get all users
	@GetMapping("/user/getall")
	public List<User> getALLUsers() {
		return userRep.findAll();
	}
	
	//find by id
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable long id) {
		return userRep.findById(id).get();
	}
	
	//add user
	@PostMapping("/user")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		
		User add= userRep.save(user);
		
		URI Uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(add.getId())
				.toUri();

		return ResponseEntity.created(Uri).build();
	}
	
	@GetMapping("/user/login/{email}/{password}")
	public String login(@PathVariable String email, @PathVariable String password ) {
		
		String tempPassword = userRep.checkpassword(email);
		if(tempPassword.equals(password)) { 
			return "true";
		}	
		else {
			return "fales";
		}	
		
	}
	
	
	
	

}
