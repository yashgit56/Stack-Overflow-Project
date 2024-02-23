package com.challengers.services.questions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.challengers.dtos.AllQuestionResponseDto;
import com.challengers.dtos.AnswerDto;
import com.challengers.dtos.QuestionDTO;
import com.challengers.dtos.singleQuestionDto;
import com.challengers.entities.Answers;
import com.challengers.entities.Questions;
import com.challengers.entities.User;
import com.challengers.repositories.AnswerRepository;
import com.challengers.repositories.ImageRepository;
import com.challengers.repositories.QuestionRepository;
import com.challengers.repositories.UserRepository;

public class QuestionServiceClass implements QuestionService {
	
	public static final int SEARCH_RESULT_PER_PAGE = 5;

	private final UserRepository userRepo ;
	
	private final QuestionRepository questionRepo ;
	
	private final AnswerRepository answerRepository;
	
	private final ImageRepository imageRepository;

	public QuestionServiceClass(UserRepository userRepo, QuestionRepository questionRepo) {
		super();
		this.userRepo = userRepo;
		this.questionRepo = questionRepo;
		this.answerRepository = null;
		this.imageRepository = null;
	}

	@Override
	public QuestionDTO addQuestion(QuestionDTO questionDTO) {
		Optional<User> optionalUser = userRepo.findById(questionDTO.getUserId());
		if(optionalUser.isPresent()) {
			Questions question = new Questions() ;
			question.setTitle(questionDTO.getTitle()) ;
			question.setBody(questionDTO.getBody());
			question.setTags(questionDTO.getTags());
			question.setCreatedDate(new Date());
			Questions createdQuestion = questionRepo.save(question) ;
			QuestionDTO createdQuestionDTO = new QuestionDTO() ;
			createdQuestionDTO.setId(createdQuestion.getId());
			createdQuestionDTO.setTitle(createdQuestion.getTitle());
			return createdQuestionDTO ;
			
		}
		return null ;
	}

	@Override
	public AllQuestionResponseDto getAllQuestions(int pageNumber) {
		// TODO Auto-generated method stub
		Pageable paging = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
		Page<Questions> questionPage = questionRepo.findAll(paging);
		AllQuestionResponseDto allQuestionResponseDto = new AllQuestionResponseDto();
		allQuestionResponseDto.setQuestionList(questionPage.getContent().stream().map(Questions::getQuestionDto).collect(Collectors.toList()));
		allQuestionResponseDto.setPageNumber(questionPage.getPageable().getPageNumber());
		allQuestionResponseDto.setTotalPages(questionPage.getTotalPages());
		return allQuestionResponseDto;
	}
	
	@Override
	public singleQuestionDto getQuestionById(Long questionId) {
		Optional<Questions> optionalQuestion = questionRepo.findById(questionId);
		
		
		if(optionalQuestion.isPresent()) {
			singleQuestionDto singlequestiondto = new singleQuestionDto();
			List<AnswerDto> answerDtoList = new ArrayList<>();
			singlequestiondto.setQuestionDto(optionalQuestion.get().getQuestionDto());
			List<Answers> answerList = answerRepository.findAllByQuestionId(questionId);
			for(Answers answer : answerList) {
				AnswerDto answerDto = answer.getAnswerDto();
				answerDto.setFile(imageRepository.findByAnswer(answer));
				answerDtoList.add(answerDto);
			}
			singlequestiondto.setAnswerDtoList(answerDtoList);
			return singlequestiondto;
		}
		return null;
	}

	@Override
	public AllQuestionResponseDto getQuestionByUserId(Long userId, int pageNumber) {
		Pageable paging = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
		Page<Questions> questionPage = questionRepo.findAllByUserId(userId,paging);
		AllQuestionResponseDto allQuestionResponseDto = new AllQuestionResponseDto();
		allQuestionResponseDto.setQuestionList(questionPage.getContent().stream().map(Questions::getQuestionDto).collect(Collectors.toList()));
		allQuestionResponseDto.setPageNumber(questionPage.getPageable().getPageNumber());
		allQuestionResponseDto.setTotalPages(questionPage.getTotalPages());
		return allQuestionResponseDto;
	}
	
	
}
