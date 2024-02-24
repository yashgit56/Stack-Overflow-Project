package com.challengers.services.answer;

import com.challengers.dtos.AnswerDto;
import com.challengers.dtos.CommentDto;

public interface AnswerService {

	AnswerDto postAnswer(AnswerDto answerDto);

	AnswerDto approveAnswer(Long answerId);

	CommentDto postCommentToAnswer(CommentDto commentDto);

}
