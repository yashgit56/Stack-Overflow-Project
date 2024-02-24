package com.challengers.services.answer;


import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.challengers.dtos.AnswerDto;
import com.challengers.dtos.CommentDto;
import com.challengers.entities.Answers;
import com.challengers.entities.Comment;
import com.challengers.entities.Questions;
import com.challengers.entities.User;
import com.challengers.repositories.AnswerRepository;
import com.challengers.repositories.CommentRepository;
import com.challengers.repositories.QuestionRepository;
import com.challengers.repositories.UserRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

	private final UserRepository userRepository;
	
	private final QuestionRepository questionRepository;
	
	private final AnswerRepository answerRepository;
	
	private final CommentRepository commentRepository;
	
	public AnswerServiceImpl(UserRepository userRepository, QuestionRepository questionRepository,
			AnswerRepository answerRepository,CommentRepository commentRepository) {
		super();
		this.userRepository = userRepository;
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
		this.commentRepository = commentRepository;
	}
	@Override
	public AnswerDto postAnswer(AnswerDto answerDto) {
		Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
		Optional<Questions> optionalQuestions = questionRepository.findById(answerDto.getQuestionId());
		if(optionalUser.isPresent() && optionalQuestions.isPresent()) {
			Answers answer = new Answers();
			answer.setBody(answerDto.getBody());
			answer.setCreatedDate(new Date());
			answer.setUser(optionalUser.get());
			answer.setQuestion(optionalQuestions.get());
			Answers createdAnswers = answerRepository.save(answer);
			AnswerDto createdAnswerDto = new AnswerDto();
			createdAnswerDto.setId(createdAnswers.getId());
			return createdAnswerDto;
		}
		return null;
	}
	
	public AnswerDto approveAnswer(Long answerId) {
		Optional<Answers> optionalAnswer = answerRepository.findById(answerId);
		if(optionalAnswer.isPresent()) {
			Answers answer = optionalAnswer.get();
			answer.setApproved(true);
			Answers updatedAnswer = answerRepository.save(answer);
			AnswerDto updatedAnswerDto = new AnswerDto();
			updatedAnswerDto.setId(updatedAnswer.getId());
			return updatedAnswerDto;
		}
		return null;
	}
	@Override
	public CommentDto postCommentToAnswer(CommentDto commentDto) {
		// TODO Auto-generated method stub
		Optional<Answers> optionalAnswer = answerRepository.findById(commentDto.getAnswerId());
		Optional<User> optionalUser = userRepository.findById(commentDto.getUserId());
		if(optionalAnswer.isPresent() && optionalUser.isPresent()) {
			Comment comment = new Comment();
			comment.setBody(commentDto.getBody());
			comment.setCreateddate(new Date());
			comment.setAnswer(optionalAnswer.get());
			Comment postedComment = commentRepository.save(comment);
			CommentDto postedCommentDto = new CommentDto();
			postedCommentDto.setId(postedComment.getId());
			return postedCommentDto;
		}
		return null;
	}
}
