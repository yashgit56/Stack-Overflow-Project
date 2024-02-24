package com.challengers.entities;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.challengers.dtos.AnswerDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Answers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name="body",length=512)
	private String body;
	
	private Date createdDate;
	
	private boolean approved = false;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="user_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy ="answer", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Comment> commentList;
	
	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="question_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Questions question;
	
	private Integer voteCount = 0;

	@OneToMany(mappedBy = "answer",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<AnswerVote> answerVoteList;
	
	public AnswerDto getAnswerDto() {
		// TODO Auto-generated method stub
		AnswerDto answerDto = new AnswerDto();
		answerDto.setId(id);
		answerDto.setBody(body);
		answerDto.setCreatedDate(createdDate);
		answerDto.setUserId(user.getId());
		answerDto.setApproved(approved);
		answerDto.setVoted(voteCount); 
		answerDto.setUsername(user.getUsername());
		answerDto.setQuestionId(question.getId());
		return answerDto;
	}

	public int getVoteCount() {
		// TODO Auto-generated method stub
		return voteCount;
	}

	public void setVoteCount(int i) {
		// TODO Auto-generated method stub
		voteCount = i;
		
	}

	public List<AnswerVote> getAnswerVoteList() {
		return answerVoteList;
	}

	public void setAnswerVoteList(List<AnswerVote> answerVoteList) {
		this.answerVoteList = answerVoteList;
	}

}
