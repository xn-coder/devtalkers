package com.xncoder.devtalker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xncoder.devtalker.Models.User;
import com.xncoder.devtalker.Repositories.AuthenticationRepo;

@Service
public class AuthenticationService {
	
	@Autowired
	private PasswordEncoder pe;
	
	@Autowired
	private AuthenticationRepo authRepo;
	
	public User getUser(String email) {
		return authRepo.findByEmail(email);
	}
	
	public User saveUser(User user) {
		user.setPassword(pe.encode(user.getPassword()));
		return authRepo.save(user);
	}

}
