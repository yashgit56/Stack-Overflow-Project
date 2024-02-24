package com.challengers.services.answer;


import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.challengers.dtos.AnswerDto;
import com.challengers.entities.Answers;
import com.challengers.entities.Questions;
import com.challengers.entities.User;
import com.challengers.repositories.AnswerRepository;
import com.challengers.repositories.QuestionRepository;
import com.challengers.repositories.UserRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

	private final UserRepository userRepository;
	
	private QuestionRepository questionRepository;
	
	private AnswerRepository answerRepository;
	
	public AnswerServiceImpl(UserRepository userRepository, QuestionRepository questionRepository,
			AnswerRepository answerRepository) {
		super();
		this.userRepository = userRepository;
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
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
}
