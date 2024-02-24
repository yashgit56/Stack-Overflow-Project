package com.challengers.services.questions;

import org.springframework.stereotype.Service;

import com.challengers.dtos.AllQuestionResponseDto;
import com.challengers.dtos.QuestionDTO;
import com.challengers.dtos.QuestionSearchResponseDto;
import com.challengers.dtos.singleQuestionDto;

@Service
public interface QuestionService {
	QuestionDTO addQuestion(QuestionDTO questionDTO) ;

	AllQuestionResponseDto getAllQuestions(int pageNumber);

	singleQuestionDto getQuestionById(Long questionId, Long userId);

	AllQuestionResponseDto getQuestionByUserId(Long userId, int pageNumber);

	QuestionSearchResponseDto searchQuestionByTitle(String title, int pageNumber);
	
	QuestionSearchResponseDto getLatestQuestion(int pageNum);
}
