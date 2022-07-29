package com.juanmi.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.juanmi.spring.models.User;
import com.juanmi.spring.repositories.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findFirstByEmail(username);
		
		UserBuilder builder = null;
		
		if(user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(false);
			builder.password(user.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			throw new UsernameNotFoundException(username + "not found.");
		}
		
		return builder.build();
	}

}
