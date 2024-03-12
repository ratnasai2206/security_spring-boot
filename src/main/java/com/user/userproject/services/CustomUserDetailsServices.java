package com.user.userproject.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.userproject.entity.Users;
import com.user.userproject.repository.UserRepository;
import com.user.userproject.util.UserRoles;

@Service
public class CustomUserDetailsServices implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=null;
		
		if(username.contains("@")) {
			user=userRepository.findByUserEmail(username);
		}else {
		 user=userRepository.findByUserName(username);
		}
		if(user ==null) {
			throw new RuntimeException();
		}
	
		return bulidUserDetails(user);
	}
	private UserDetails bulidUserDetails(Users user) {
		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		UserRoles userRoles=user.getUserRoles();
		
		authorities.add(new SimpleGrantedAuthority(userRoles.toString()));

		return new User(user.getUserName(), new BCryptPasswordEncoder().encode(user.getPassword()), authorities);
	}

}
