package com.xncoder.devtalker.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "answers")
public class Answer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String answer;
	
	@ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@Column
	private boolean isAccepted;
	
	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt = new java.util.Date();
	
	@ManyToMany
    @JoinTable(
        name = "answer_vote",
        joinColumns = @JoinColumn(name = "question_id", nullable=true),
        inverseJoinColumns = @JoinColumn(name = "user_id", nullable=true)
    )
    private List<User> voters = new ArrayList<User>();
	
	public List<User> getVoters() {
		return voters;
	}

	public void setVoters(List<User> voters) {
		this.voters = voters;
	}

	@ManyToMany
    @JoinTable(
        name = "answer_count",
        joinColumns = @JoinColumn(name = "answer_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> answers = new ArrayList<User>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public java.util.Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<User> getAnswers() {
		return answers;
	}

	public void setAnswers(List<User> answers) {
		this.answers = answers;
	}
}
