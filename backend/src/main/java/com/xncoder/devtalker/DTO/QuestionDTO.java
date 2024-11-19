package com.xncoder.devtalker.DTO;

import java.util.ArrayList;

public class QuestionDTO {
	private Long id;
	private String title;
	private String description;
	private ArrayList<String> tags;
	private String date;
	private int votes;
	private int views;
	private Long uid;
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public QuestionDTO(Long id, String title, String description, ArrayList<String> tags, String date, int votes, int views, Long uid) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.tags = tags;
		this.date = date;
		this.votes = votes;
		this.views = views;
		this.uid = uid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
