package com.user.userproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userproject.dto.ResponseStructure;
import com.user.userproject.entity.Users;
import com.user.userproject.payload.UserDto;
import com.user.userproject.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

	
	@Autowired
	private UserService service;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Users>> createUser(@RequestBody UserDto userDto) {
		
		return service.createUser(userDto);
	}
	
//	@PreAuthorize("hasRole('ADMINE')")
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Users>>> getAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/{user_Id}")
	public ResponseEntity<ResponseStructure<Users>> getUserById(@PathVariable int user_Id){
		return service.getUser(user_Id);
	}
}
