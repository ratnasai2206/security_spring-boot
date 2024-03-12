package com.user.userproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.userproject.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	
	Users findByUserName(String userName);
	
	Users findByUserEmail(String userEmail);
	
}
