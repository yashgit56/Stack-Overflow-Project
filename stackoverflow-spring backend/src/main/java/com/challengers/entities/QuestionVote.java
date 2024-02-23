//package com.challengers.entities;
//
//import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import com.challengers.enums.VoteType;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.Data;
//
//@Entity
//@Data
//public class QuestionVote {
//	
//	private Long id;
//	private VoteType voteType;
//	
//	@ManyToOne(fetch = FetchType.LAZY,optional = false)
//	@JoinColumn(name = "user_id",nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnore
//	private User user;
//	
//	@ManyToOne(fetch = FetchType.LAZY,optional = false)
//	@JoinColumn(name = "question_id",nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnore
//	private Question question;
//}
