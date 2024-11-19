package com.xncoder.devtalker.DTO;

import java.util.ArrayList;

public class AllQuestionDTO {
	
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String text;
	private String description;
	private ArrayList<String> tags;
	private String date;
	private boolean isAnswered;
	private int votes;
	private int answers;
	private int views;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isAnswered() {
		return isAnswered;
	}
	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getAnswers() {
		return answers;
	}
	public void setAnswers(int answers) {
		this.answers = answers;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public AllQuestionDTO(Long id, String text, String description, ArrayList<String> tags, String date,
			boolean isAnswered, int votes, int answers, int views) {
		super();
		this.id = id;
		this.text = text;
		this.description = description;
		this.tags = tags;
		this.date = date;
		this.isAnswered = isAnswered;
		this.votes = votes;
		this.answers = answers;
		this.views = views;
	}
	

}
