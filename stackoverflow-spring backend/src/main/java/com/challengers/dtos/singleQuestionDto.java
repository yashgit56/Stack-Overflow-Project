package com.challengers.dtos;

import java.util.List;

import lombok.Data;

@Data
public class singleQuestionDto {
	private QuestionDTO  questionDto;

	private List<AnswerDto> answerDtoList;
	public Object setQuestionDto(QuestionDTO questionDto2) {
		// TODO Auto-generated method stub
		return null;
	}
	public void setAnswerDtoList(List<AnswerDto> answerDtoList2) {
		// TODO Auto-generated method stub
		
	}
}
