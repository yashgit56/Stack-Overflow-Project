package com.challengers.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.challengers.enums.VoteType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class AnswerVote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private VoteType voteType;
	
	public VoteType getVoteType() {
		return voteType;
	}

	public Answers getAnswer() {
		return answer;
	}

	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "answer_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	private Answers answer;

	public void setVoteType(VoteType voteType2) {
		// TODO Auto-generated method stub
		voteType = voteType2;
		
	}

	public void setAnswer(Answers existingAnswer) {
		// TODO Auto-generated method stub
		answer = existingAnswer;
	}

	public void setUser(User existingUser) {
		// TODO Auto-generated method stub
		user = existingUser;
		
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
