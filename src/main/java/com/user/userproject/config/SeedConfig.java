package com.user.userproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.user.userproject.entity.Users;
import com.user.userproject.repository.UserRepository;
import com.user.userproject.util.UserRoles;




@Configuration
public class SeedConfig implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	@Override
	public void run(String... args) throws Exception {
		if(repository.count()==0) {
			Users user=new Users();
			user.setUserName("admine");
			user.setUserEmail("admine@gmail.com");
			user.setPassword("Admine@123");
			user.setUserRoles(UserRoles.ADMINE);
			
			repository.save(user);
		}
	}

}
