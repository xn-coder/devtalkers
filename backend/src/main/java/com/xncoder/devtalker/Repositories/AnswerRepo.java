package com.xncoder.devtalker.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xncoder.devtalker.Models.Answer;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long>{
	
	List<Answer> findByQuestionId(Long id);
	
	List<Answer> findByUserId(Long id);

}
