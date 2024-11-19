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
import com.xncoder.devtalker.DTO.QuestionDTO;
import com.xncoder.devtalker.Services.QuestionsService;

@RestController
@RequestMapping("/api")
public class QuestionsController {
	
	@Autowired
	private QuestionsService qs;
	
	@Autowired
	private JwtSecurity security;
	
	@PostMapping("/question/post")
	public ResponseEntity<?> postQuestion(@RequestHeader("Authorization") String token, @RequestBody QuestionDTO question) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
		return ResponseEntity.ok(qs.saveQuestion(security.extractUsername(token), question));
	}
	
	@GetMapping("/question/getall")
	public ResponseEntity<?> getAllQuestion() {
		return ResponseEntity.ok(qs.getAllQuestions());
	}
	
	@GetMapping("/question/get/{id}")
	public ResponseEntity<?> getQuestion(@PathVariable Long id) {
		return ResponseEntity.ok(qs.getQuestion(id));
	}
	
	@GetMapping("/question/getuser/{id}")
	public ResponseEntity<?> getUserQuestion(@PathVariable Long id) {
		return ResponseEntity.ok(qs.getQuestionByUser(id));
	}
	
	@GetMapping("/question/search/{search}")
	public ResponseEntity<?> getUserQuestion(@PathVariable String search) {
		return ResponseEntity.ok(qs.searchQuestion(search));
	}
	
	@PostMapping("/question/vote/up")
	public ResponseEntity<?> addVote(@RequestHeader("Authorization") String token, @RequestBody HashMap<String, Long> data) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
		return ResponseEntity.ok(qs.votesQuestion(data.get("qid"), security.extractUsername(token), "up"));
	}
	
	@PostMapping("/question/vote/down")
	public ResponseEntity<?> removeVote(@RequestHeader("Authorization") String token, @RequestBody HashMap<String, Long> data) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
		return ResponseEntity.ok(qs.votesQuestion(data.get("qid"), security.extractUsername(token), "down"));
	}
	
	@PostMapping("/question/view")
	public ResponseEntity<?> viewQuestion(@RequestHeader("Authorization") String token, @RequestBody HashMap<String, Long> data) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
		return ResponseEntity.ok(qs.viewQuestions(security.extractUsername(token), data.get("qid")));
	}
	
	@PostMapping("/answer/accept")
	public ResponseEntity<?> acceptAnswer(@RequestHeader("Authorization") String token, @RequestBody HashMap<String, Long> data) {
		if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
		return ResponseEntity.ok(qs.acceptAnswer(security.extractUsername(token), data.get("aid"), data.get("qid")));
	}

}
