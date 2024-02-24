package com.challengers.services.vote;

import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.stereotype.Service;

import com.challengers.dtos.AnswerVoteDto;
import com.challengers.dtos.QuestionVoteDto;
import com.challengers.entities.AnswerVote;
import com.challengers.entities.Answers;
import com.challengers.entities.QuestionVote;
import com.challengers.entities.Questions;
import com.challengers.entities.User;
import com.challengers.enums.VoteType;
import com.challengers.repositories.AnswerRepository;
import com.challengers.repositories.AnswerVoteRepository;
import com.challengers.repositories.QuestionRepository;
import com.challengers.repositories.QuestionVoteRepository;
import com.challengers.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

	private final QuestionVoteRepository questionVoteRepository;

	private final UserRepository userRepository;
	
	private final QuestionRepository questionRepository;
	
	private final AnswerVoteRepository answerVoteRepository;
	
	private final AnswerRepository answerRepository;
	
	public VoteServiceImpl(QuestionVoteRepository questionVoteRepository, UserRepository userRepository,
			QuestionRepository questionRepository,AnswerVoteRepository answerVoteRepository,AnswerRepository answerRepository) {
		super();
		this.questionVoteRepository = questionVoteRepository;
		this.userRepository = userRepository;
		this.questionRepository = questionRepository;
		this.answerVoteRepository = answerVoteRepository;
		this.answerRepository = answerRepository;
	}

	@Override
	public QuestionVoteDto addVoteToQuestion(QuestionVoteDto questionVoteDto) {
		Optional<User> optionalUser = userRepository.findById(questionVoteDto.getUserId());
		Optional<Questions> optionalQuestion = questionRepository.findById(questionVoteDto.getQuestionId());
		if(optionalQuestion.isPresent() && optionalUser.isPresent()) {
			QuestionVote questionVote = new QuestionVote();
			Questions existingQuestion = optionalQuestion.get();
			questionVote.setVoteType(questionVoteDto.getVoteType());
			if(questionVote.getVoteType() == VoteType.UPVOTE) {
				existingQuestion.setVoteCount(existingQuestion.getVoteCount() + 1);
			}
			else {
				existingQuestion.setVoteCount(existingQuestion.getVoteCount() - 1);
			}
			questionVote.setQuestion(optionalQuestion.get());
			questionVote.setUser(optionalUser.get());
			questionRepository.save(existingQuestion);
			QuestionVote votedQuestion = questionVoteRepository.save(questionVote);
			QuestionVoteDto questionVotedDto = new QuestionVoteDto();
			questionVotedDto.setId(votedQuestion.getId());
			return questionVotedDto;
		}
		return null;
	}

	@Override
	public AnswerVoteDto addVoteToAnswer(AnswerVoteDto answerVoteDto) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findById(answerVoteDto.getUserId());
		Optional<Answers> optionalAnswer = answerRepository.findById(answerVoteDto.getAnswerId()); 
		if(optionalAnswer.isPresent() && optionalUser.isPresent()) {
			Answers existingAnswer = optionalAnswer.get();
			User existingUser = optionalUser.get();
			AnswerVote answerVote = new AnswerVote();
			answerVote.setVoteType(answerVoteDto.getVoteType());
			answerVote.setAnswer(existingAnswer);
			answerVote.setUser(existingUser);
			if(answerVoteDto.getVoteType() == VoteType.UPVOTE) {
				existingAnswer.setVoteCount(existingAnswer.getVoteCount() + 1);
			}
			existingAnswer.setVoteCount(existingAnswer.getVoteCount() - 1);
			answerRepository.save(existingAnswer);
			AnswerVote createdAnswerVote = answerVoteRepository.save(answerVote);
			AnswerVoteDto createdAnswerVoteDto = new AnswerVoteDto();
			createdAnswerVoteDto.setId(createdAnswerVote.getId());
			return createdAnswerVoteDto;
		}
		return null;
	}
	
}
