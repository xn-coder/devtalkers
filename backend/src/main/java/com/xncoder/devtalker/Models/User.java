package com.xncoder.devtalker.Models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    
    @CreationTimestamp
    private LocalDateTime date;
    
    @Column(columnDefinition = "LONGBLOB")
	private String image = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUA" + // Base64 content
            "AAAABCAAAAAAElFTkSuQmCC";
    
    @Column
    private String location;
    
    @Column
    private String title;
    
    @Column
    private String about;
    
    @Column
    private String web_link;
    
    @Column
    private String twi_link;
    
    @Column
    private String git_link;
    
    @Column
    private String full_name;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<Question>();
    
    @ManyToMany(mappedBy = "voters")
    private List<Question> votedQuestions;
    
    @ManyToMany(mappedBy = "viewers")
    private List<Question> viewedQuestions;

    @OneToMany(mappedBy = "answers")
    private List<Answer> answers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getWeb_link() {
		return web_link;
	}

	public void setWeb_link(String web_link) {
		this.web_link = web_link;
	}

	public String getTwi_link() {
		return twi_link;
	}

	public void setTwi_link(String twi_link) {
		this.twi_link = twi_link;
	}

	public String getGit_link() {
		return git_link;
	}

	public void setGit_link(String git_link) {
		this.git_link = git_link;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Question> getVotedQuestions() {
		return votedQuestions;
	}

	public void setVotedQuestions(List<Question> votedQuestions) {
		this.votedQuestions = votedQuestions;
	}

	public List<Question> getViewedQuestions() {
		return viewedQuestions;
	}

	public void setViewedQuestions(List<Question> viewedQuestions) {
		this.viewedQuestions = viewedQuestions;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
