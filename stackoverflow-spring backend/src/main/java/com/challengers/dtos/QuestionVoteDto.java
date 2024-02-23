package com.challengers.dtos;

import com.challengers.enums.VoteType;

import lombok.Data;

@Data
public class QuestionVoteDto {
	
	private Long id;
	
	private VoteType voteType;
	
	private Long userId;
	
	private Long questionId;
}
