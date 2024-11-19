package com.xncoder.devtalker.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xncoder.devtalker.DTO.AnswerDTO;
import com.xncoder.devtalker.Models.Answer;
import com.xncoder.devtalker.Models.Question;
import com.xncoder.devtalker.Models.User;
import com.xncoder.devtalker.Repositories.AnswerRepo;
import com.xncoder.devtalker.Repositories.QuestionsRepo;
import com.xncoder.devtalker.Repositories.UserRepo;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepo ar;
	
	@Autowired
	private QuestionsRepo qr;
	
	@Autowired
	private UserRepo ur;
	
	public Answer saveAnswer(String email, AnswerDTO ans) {
		Answer as = new Answer();
		as.setAnswer(ans.getAnswer());
		
		Question que = qr.findById(ans.getQid()).orElse(null);
		List<Answer> question = que.getAnswers();
		question.add(as);
		que.setAnswers(question);
		as.setQuestion(que);
		
		User user = ur.findByEmail(email);
		List<Answer> answer = user.getAnswers();
		answer.add(as);
		user.setAnswers(answer);
		as.setUser(user);
		
		return ar.save(as);
	}
	
	public List<AnswerDTO> getAnswers(Long qid) {
		List<AnswerDTO> data = new ArrayList<AnswerDTO>();
		for(Answer ans: ar.findByQuestionId(qid)) {
			data.add(new AnswerDTO(ans.getId(), ans.getAnswer(), ans.getQuestion().getId(), ans.getUser().getId(), ans.getCreatedAt().toString(), ans.getUser().getImage(), ans.getUser().getName(), ans.getVoters().size(), ans.isAccepted()));
		}
		return data;
	}
	
	public List<HashMap<String, String>> getAnswerByUserId(Long id) {
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		for(Answer ans: ar.findByUserId(id)) {
			HashMap<String, String> an = new HashMap<String, String>();
			an.put("id", ""+ans.getQuestion().getId());
			an.put("answer", ans.getAnswer());
			data.add(an);
		}
		return data;
	}
	
	public HashMap<String, String> votesAnswer(Long aid, String email, String direction) {
		HashMap<String, String> data = new HashMap<String, String>();
		Answer ans =  ar.findById(aid).orElse(null);
		User user = ur.findByEmail(email);
		
		if(direction == "up") {
			if(ans.getVoters().indexOf(user) == -1) {				
				ans.getVoters().add(user);
				data.put("data", "added");
			} else {
				data.put("data", "already");
			}
		} else {
			if(ans.getVoters().indexOf(user) != -1) {				
				ans.getVoters().remove(user);
				data.put("data", "removed");
			} else {
				data.put("data", "not");
			}
			
		}
		
		ar.save(ans);
		
		data.put("status", "success");
		
		return data;
	}
}
