package com.xncoder.devtalker.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "questions")
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	@Lob
	@Column(nullable = false, columnDefinition = "LONGTEXT")
    private String question;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

	@Column
    private Boolean isAnswered = false;
	
	@CreationTimestamp
    private LocalDateTime date;
	
	@Lob
	@Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;
	
	@ManyToMany
    @JoinTable(
        name = "tag",
        joinColumns = @JoinColumn(name = "question_id", nullable=true),
        inverseJoinColumns = @JoinColumn(name = "tag_id", nullable=true)
    )
    private List<Tag> tag = new ArrayList<Tag>();
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdAt = new java.util.Date();
	
	@ManyToMany
    @JoinTable(
        name = "question_vote",
        joinColumns = @JoinColumn(name = "question_id", nullable=true),
        inverseJoinColumns = @JoinColumn(name = "user_id", nullable=true)
    )
    private List<User> voters = new ArrayList<User>();
	
	@ManyToMany
    @JoinTable(
        name = "view_count",
        joinColumns = @JoinColumn(name = "question_id", nullable=true),
        inverseJoinColumns = @JoinColumn(name = "user_id", nullable=true)
    )
    private List<User> viewers = new ArrayList<User>();
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Boolean getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(Boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Tag> getTags() {
		return tag;
	}

	public void setTags(List<Tag> tags) {
		this.tag = tags;
	}

	public java.util.Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<User> getVoters() {
		return voters;
	}

	public void setVoters(List<User> voters) {
		this.voters = voters;
	}

	public List<User> getViewers() {
		return viewers;
	}

	public void setViewers(List<User> viewers) {
		this.viewers = viewers;
	}
}
