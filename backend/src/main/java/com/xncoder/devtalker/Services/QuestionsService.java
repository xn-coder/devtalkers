package com.xncoder.devtalker.Services;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xncoder.devtalker.DTO.AllQuestionDTO;
import com.xncoder.devtalker.DTO.QuestionDTO;
import com.xncoder.devtalker.Models.Answer;
import com.xncoder.devtalker.Models.Question;
import com.xncoder.devtalker.Models.Tag;
import com.xncoder.devtalker.Models.User;
import com.xncoder.devtalker.Repositories.AnswerRepo;
import com.xncoder.devtalker.Repositories.QuestionsRepo;
import com.xncoder.devtalker.Repositories.TagRepo;
import com.xncoder.devtalker.Repositories.UserRepo;

@Service
public class QuestionsService {
	
	@Autowired
	private QuestionsRepo qr;
	
	@Autowired 
	private UserRepo ur;
	
	@Autowired
	private AnswerRepo ar;
	
	@Autowired
	private TagRepo tr;
	
	public HashMap<String, String> saveQuestion(String email, QuestionDTO qs) {
		
		Question question = new Question();
		question.setQuestion(qs.getTitle());
		question.setDescription(qs.getDescription());
		
		List<Tag> tags = question.getTags();
		for(String tagname: qs.getTags()) {
			Tag tag = tr.findByName(tagname);
			List<Question> tagQuestion = tag.getQuestions();
			tagQuestion.add(question);
			tag.setQuestions(tagQuestion);
			tr.save(tag);
			tags.add(tag);
		}
		question.setTags(tags);
		
		User user = ur.findByEmail(email);
		question.setUser(user);
		
		List<Question> userQuestion = user.getQuestions();
		userQuestion.add(question);
		user.setQuestions(userQuestion);
		
		qr.save(question);
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("status", "success");
		return data;
	}
	
	public List<AllQuestionDTO> getAllQuestions() {
		List<AllQuestionDTO> data = new ArrayList<AllQuestionDTO>();
		for(Question quesition: qr.findAll()) {
			ArrayList<String> tags = new ArrayList<String>();
			for(Tag tag: quesition.getTags())
				tags.add(tag.getName());
			data.add(new AllQuestionDTO(quesition.getId(), quesition.getQuestion(), quesition.getDescription(), tags, quesition.getDate().toString(), quesition.getIsAnswered(), quesition.getVoters().size(), quesition.getAnswers().size(), quesition.getViewers().size()));
		}
		return data;
	}
	
	public QuestionDTO getQuestion(Long id) {
		Question qu = qr.findById(id).orElse(null);
		ArrayList<String> tags = new ArrayList<String>();
		for(Tag tag: qu.getTags())
			tags.add(tag.getName());
		
		QuestionDTO q = new QuestionDTO(qu.getId(), qu.getQuestion(), qu.getDescription(), tags, qu.getDate().toString(), qu.getVoters().size(), qu.getViewers().size(), qu.getUser().getId());
		return q;
	}
	
	public List<HashMap<String, String>> getQuestionByUser(Long id) {
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		for(Question question: qr.findAllByUserId(id)) {
			HashMap<String, String> que = new HashMap<String, String>();
			que.put("id", ""+question.getId());
			que.put("question", question.getQuestion());
			data.add(que);
		}
		return data;
	}
	
	public List<AllQuestionDTO> searchQuestion(String title) {
		List<AllQuestionDTO> questionSearchResponseDTO = new ArrayList<AllQuestionDTO>();
		
        for(Question que: qr.findAll()) {
        	if(que.getQuestion().startsWith(title)) {
        		
        		ArrayList<String> tags = new ArrayList<String>();
        		for(Tag tg: que.getTags())
        			tags.add(tg.getName());
        		questionSearchResponseDTO.add(new AllQuestionDTO(que.getId(), que.getQuestion(), que.getDescription(), tags, que.getDate().toString(), que.getIsAnswered(), que.getVoters().size(), que.getAnswers().size(), que.getViewers().size()));
        	} else {
        		
        		for(Tag tag: que.getTags()) {
        			if(tag.getName().startsWith(title)) {        				
        				ArrayList<String> tags = new ArrayList<String>();
        				for(Tag tg: que.getTags())
        					tags.add(tg.getName());
        				questionSearchResponseDTO.add(new AllQuestionDTO(que.getId(), que.getQuestion(), que.getDescription(), tags, que.getDate().toString(), que.getIsAnswered(), que.getVoters().size(), que.getAnswers().size(), que.getViewers().size()));
        			}
        		}
        	}
        	
        	
        }
	        
	    return questionSearchResponseDTO;
	}
	
	public HashMap<String, String> votesQuestion(Long qid, String email, String direction) {
		HashMap<String, String> data = new HashMap<String, String>();
		Question que =  qr.findById(qid).orElse(null);
		User user = ur.findByEmail(email);
		
		if(direction == "up") {
			if(que.getVoters().indexOf(user) == -1) {				
				que.getVoters().add(user);
				data.put("data", "added");
			} else {
				data.put("data", "already");
			}
		} else {
			if(que.getVoters().indexOf(user) != -1) {				
				que.getVoters().remove(user);
				data.put("data", "removed");
			} else {
				data.put("data", "not");
			}
			
		}
		
		qr.save(que);
		
		data.put("status", "success");
		
		return data;
	}
	
	public HashMap<String, String> viewQuestions(String email, Long qid) {
		HashMap<String, String> data = new HashMap<String, String>();
		Question que =  qr.findById(qid).orElse(null);
		User user = ur.findByEmail(email);
		
		if(que.getViewers().indexOf(user) == -1) {
			que.getViewers().add(user);
			qr.save(que);
			data.put("data", "added");
		} else {
			data.put("data", "already");
		}
		
		return data;
	}
	
	public HashMap<String, String> acceptAnswer(String email, Long aid, Long qid) {
		HashMap<String, String> data = new HashMap<String, String>();
		Question que =  qr.findById(qid).orElse(null);
		Answer ans = ar.findById(aid).orElse(null);
		
		que.setIsAnswered(true);
		ans.setAccepted(true);
		
		qr.save(que);
		ar.save(ans);
		
		data.put("status", "marked");
		
		return data;
	}
}
