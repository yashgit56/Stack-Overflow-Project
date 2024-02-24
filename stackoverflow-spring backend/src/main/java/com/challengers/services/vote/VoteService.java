package com.challengers.services.vote;

import com.challengers.dtos.AnswerVoteDto;
import com.challengers.dtos.QuestionVoteDto;

public interface VoteService {

	public QuestionVoteDto addVoteToQuestion(QuestionVoteDto questionVoteDto);
	
	AnswerVoteDto addVoteToAnswer(AnswerVoteDto answerVoteDto);
}
