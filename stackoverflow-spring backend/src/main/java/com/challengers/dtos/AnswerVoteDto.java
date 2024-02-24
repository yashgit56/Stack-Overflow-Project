package com.challengers.dtos;

import com.challengers.enums.VoteType;

import lombok.Data;

@Data
public class AnswerVoteDto {

	private Long id;
	
	private VoteType voteType;
	
	private Long userId;
	
	private Long questionId;

	private Long answerId;
	
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

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

	public Long getId() {
		return id;
	} 

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAnswerId() {
		// TODO Auto-generated method stub
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
}
