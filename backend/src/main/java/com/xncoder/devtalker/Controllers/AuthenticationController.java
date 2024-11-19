package com.xncoder.devtalker.Controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xncoder.devtalker.JwtSecurity;
import com.xncoder.devtalker.Models.User;
import com.xncoder.devtalker.Services.AuthenticationService;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private JwtSecurity security;
	
	@Autowired
	private PasswordEncoder ps;

	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
		User us = authService.getUser(user.getEmail());
		if(us == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email not registered");
		} else if(!ps.matches(user.getPassword(), us.getPassword())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid password");
		} else {
			String token = security.generateToken(user.getEmail());
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("token", token);
			return ResponseEntity.ok(data);
		}
    }
	
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
		User us = authService.getUser(user.getEmail());
		if(us != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
		} else {
			return ResponseEntity.ok(authService.saveUser(user));
		}
    }
}
