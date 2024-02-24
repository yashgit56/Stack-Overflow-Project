package com.challengers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengers.dtos.AnswerDto;
import com.challengers.services.answer.AnswerService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

	private final AnswerService answerService;
	
	public AnswerController(AnswerService answerService) {
		this.answerService = answerService;
	}
	@PostMapping
	public ResponseEntity<?> addAnswer(@RequestBody AnswerDto answerDto){
		AnswerDto createdAnswerDto = answerService.postAnswer(answerDto);
		if(createdAnswerDto == null) {
			return new ResponseEntity<>("Something went wrong.",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswerDto);
	}
	
	@GetMapping("/answer/{answerId}")
	public ResponseEntity<AnswerDto> approveAnswer(@PathVariable Long answerId){
		AnswerDto approveAnswerDto = answerService.approveAnswer(answerId);
		if(approveAnswerDto == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(approveAnswerDto);
	}
}
