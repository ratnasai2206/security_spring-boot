package com.user.userproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.user.userproject.dto.ResponseStructure;
import com.user.userproject.entity.Users;
import com.user.userproject.payload.UserDto;
import com.user.userproject.repository.UserRepository;
import com.user.userproject.util.UserRoles;

@Repository
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<ResponseStructure<Users>> createUser(UserDto dto) {
		
		Users users=new Users();
		users.setUserName(dto.getUserName());
		users.setUserEmail(dto.getUserEmail());
		users.setPassword(dto.getPassword());
		users.setUserRoles(UserRoles.USER);
		
		Users recivedUsers=userRepository.save(users);
		
		ResponseStructure<Users> responseStructure=new ResponseStructure<Users>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(recivedUsers);
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Users>>> getAllUsers() {
		
		List<Users> users=userRepository.findAll();
		
		ResponseStructure<List<Users>> responseStructure=new ResponseStructure<List<Users>>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("List Of Users");
		responseStructure.setData(users);
		return new ResponseEntity<ResponseStructure<List<Users>>>(responseStructure,HttpStatus.OK);
	}
	
	
	public ResponseEntity<ResponseStructure<Users>> getUser(int user_Id){
		
		Optional<Users> optional=userRepository.findById(user_Id);
		if(optional.isPresent()) {
			ResponseStructure<Users> responseStructure=new ResponseStructure<Users>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Users Details");
			responseStructure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure,HttpStatus.OK);
		}else {
			ResponseStructure<Users> responseStructure=new ResponseStructure<Users>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Users Not Found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Users>>(responseStructure,HttpStatus.OK);
		}
		
	}
	
	}
