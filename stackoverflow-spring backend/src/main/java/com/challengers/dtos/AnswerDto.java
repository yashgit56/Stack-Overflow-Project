package com.challengers.dtos;

import java.util.Date;

import com.challengers.entities.Image;

public class AnswerDto {
	private Long id;
	private String body;
	private Date createdDate;
	private Long questionId;
	private Long userId;
	private String username;
	private Image file;
	private boolean approved;
	
	private int voted;
	
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Image getFile() {
		return file;
	}
//	public void setFile(Image file) {
//		this.file = file;
//	}
	public void setFile(Image byAnswer) {
		this.file = byAnswer;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getVoted() {
		return voted;
	}
	public void setVoted(int voted) {
		this.voted = voted;
	}
	
	
}
