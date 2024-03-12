package com.user.userproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.user.userproject.util.UserRoles;

@Configuration
//@EnableMethodSecurity
public class AppConfiguration {

	//disabling csrf, removing from based authentication 
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http )throws Exception {
//		http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST,"/users").permitAll().requestMatchers(HttpMethod.GET,"/users/**").authenticated());
//		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		http.httpBasic();
		http.csrf(csrf -> {
			try {
				csrf.disable().authorizeHttpRequests().requestMatchers("/users").permitAll().requestMatchers("/users/all").hasAnyAuthority(UserRoles.ADMINE.toString()).and().httpBasic();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	
		return http.build();
	}
	
	// creating multiple user details and storing them
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user= User.withUsername("Ratna").password("{noop}Ratna@123").build();
//		var admin=User.withUsername("admin").password("{noop}admin").build();
//		return new InMemoryUserDetailsManager(user,admin);
//	}
	@Bean 
	public PasswordEncoder encoder() { 
		return new BCryptPasswordEncoder();
	}
}
