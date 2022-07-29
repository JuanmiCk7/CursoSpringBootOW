package com.juanmi.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.juanmi.spring.models.User;
import com.juanmi.spring.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public User insert(User u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return repository.save(u);
	}
	
	public void delete(long id) {
		repository.deleteById(id);
	}
	
	public void delete(User u) {
		repository.delete(u);
	}
	
	public User edit(User u) {
		return repository.save(u);
	}
	
	public User findById(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findByEmail(String email) {
		return repository.findFirstByEmail(email);
	}
	
}
