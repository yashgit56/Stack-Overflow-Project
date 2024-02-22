package com.challengers.dtos;

import java.util.List;

import lombok.Data;

@Data
public class AllQuestionResponseDto {
	private List<QuestionDTO> questionList;
	
	private Integer totalPages;
	
	private Integer pageNumber;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List<QuestionDTO> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionDTO> questionList) {
		this.questionList = questionList;
	}
}
