package com.xncoder.devtalker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xncoder.devtalker.JwtSecurity;
import com.xncoder.devtalker.Models.User;
import com.xncoder.devtalker.Services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private JwtSecurity security;
	
	@Autowired
	private UserService useServe;
	
	@GetMapping("/user")
	public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
		return ResponseEntity.ok(useServe.getProfile(security.extractUsername(token)));
    }
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		return ResponseEntity.ok(useServe.getProfileById(id));
    }
	
	@GetMapping("/user/getall")
	public ResponseEntity<?> getUser() {
		return ResponseEntity.ok(useServe.getAllProfile());
    }
	
	@PostMapping("/user/update")
	public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
		return ResponseEntity.ok(useServe.updateProfile(security.extractUsername(token), user));
    }

}
