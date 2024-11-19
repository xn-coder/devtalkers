package com.xncoder.devtalker.Controllers;

import java.util.HashMap;

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
import com.xncoder.devtalker.DTO.AnswerDTO;
import com.xncoder.devtalker.Services.AnswerService;

@RestController
@RequestMapping("/api")
public class AnswerController {
	@Autowired
	private AnswerService as;
	
	@Autowired
	private JwtSecurity security;
	
	@GetMapping("/answer/get/{id}")
	public ResponseEntity<?> getAnswer(@PathVariable Long id) {
		return ResponseEntity.ok(as.getAnswers(id));
	}
	
	@GetMapping("/answer/getuser/{id}")
	public ResponseEntity<?> getAnswerByUser(@PathVariable Long id) {
		return ResponseEntity.ok(as.getAnswerByUserId(id));
	}
	
	@PostMapping("/answer/add")
	public ResponseEntity<?> addAnswer(@RequestHeader("Authorization") String token, @RequestBody AnswerDTO ans) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
		return ResponseEntity.ok(as.saveAnswer(security.extractUsername(token), ans));
	}
	
	@PostMapping("/answer/vote/up")
	public ResponseEntity<?> addVote(@RequestHeader("Authorization") String token, @RequestBody HashMap<String, Long> data) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
		return ResponseEntity.ok(as.votesAnswer(data.get("aid"), security.extractUsername(token), "up"));
	}
	
	@PostMapping("/answer/vote/down")
	public ResponseEntity<?> removeVote(@RequestHeader("Authorization") String token, @RequestBody HashMap<String, Long> data) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
		return ResponseEntity.ok(as.votesAnswer(data.get("aid"), security.extractUsername(token), "down"));
	}
}
