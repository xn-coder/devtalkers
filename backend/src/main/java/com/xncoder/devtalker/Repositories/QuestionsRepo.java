package com.xncoder.devtalker.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xncoder.devtalker.Models.Question;

@Repository
public interface QuestionsRepo extends JpaRepository<Question, Long>{
	Question findByUserId(Long id);
	List<Question> findAllByUserId(Long id);
	List<Question> findAllByQuestion(String search);
	
	
}
