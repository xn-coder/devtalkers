package com.xncoder.devtalker.DTO;

public class AnswerDTO {
	private Long id;
	private String answer;
	private Long qid;
	private Long uid;
	private int votes;
	private String date;
	private String image;
	private String name;
	private boolean isAccept;
	public AnswerDTO(Long id, String answer, Long qid, Long uid, String date, String image, String name, int votes, boolean isAccept) {
		super();
		this.id = id;
		this.answer = answer;
		this.qid = qid;
		this.uid = uid;
		this.date = date;
		this.image = image;
		this.name = name;
		this.votes = votes;
		this.isAccept = isAccept;
	}
	public boolean isAccept() {
		return isAccept;
	}
	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public Long getQid() {
		return qid;
	}
	public void setQid(Long qid) {
		this.qid = qid;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
