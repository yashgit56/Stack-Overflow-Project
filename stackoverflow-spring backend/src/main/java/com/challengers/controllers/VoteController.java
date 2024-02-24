package com.challengers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengers.dtos.AnswerVoteDto;
import com.challengers.dtos.QuestionVoteDto;
import com.challengers.services.vote.VoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VoteController {
	
	private final VoteService voteService;
	
	public VoteController(VoteService voteService) {
		super();
		this.voteService = voteService;
	}

	public VoteService getVoteService() {
		return voteService;
	}
	
	@PostMapping("/vote")
	public ResponseEntity<?> addVoteToQuestion(@RequestBody QuestionVoteDto questionVoteDto){
		QuestionVoteDto questionVotedDto = voteService.addVoteToQuestion(questionVoteDto);
		if(questionVotedDto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went Wrong");
		return ResponseEntity.status(HttpStatus.CREATED).body(questionVotedDto);
	}
	
	@PostMapping("/answer-vote")
	public ResponseEntity<?> addVoteToAnswer(@PathVariable AnswerVoteDto answerVoteDto){
		AnswerVoteDto createdAnswerVoteDto = voteService.addVoteToAnswer(answerVoteDto);
		if(createdAnswerVoteDto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswerVoteDto);
	}
}
