package com.challengers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengers.dtos.AllQuestionResponseDto;
import com.challengers.dtos.QuestionDTO;
import com.challengers.dtos.singleQuestionDto;
import com.challengers.services.questions.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionsController {
	
	private final QuestionService questionService ;

	public QuestionsController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}
	
	public ResponseEntity<?> postQuestion(@RequestBody QuestionDTO questionDTO){
		QuestionDTO createdQuestionDTO = questionService.addQuestion(questionDTO) ;
		if(createdQuestionDTO == null) {
			return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestionDTO) ;
		
	}
	
	@GetMapping("/questions/{pageNumber}")
	public ResponseEntity<AllQuestionResponseDto> getAllQuestions(@PathVariable int pageNumber){
		AllQuestionResponseDto allQuestionResponseDto = questionService.getAllQuestions(pageNumber);
		return ResponseEntity.ok(allQuestionResponseDto);
	}
	@GetMapping("/question/{questionId}")
	public ResponseEntity<?> getQuestionById(@PathVariable Long questionId){
		singleQuestionDto singlequestionDto = questionService.getQuestionById(questionId);
		if(singlequestionDto == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singlequestionDto);
	}
}
