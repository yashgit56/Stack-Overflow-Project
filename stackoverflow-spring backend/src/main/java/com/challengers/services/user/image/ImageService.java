package com.challengers.services.user.image;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.challengers.entities.Answers;
import com.challengers.entities.Image;
import com.challengers.repositories.AnswerRepository;
import com.challengers.repositories.ImageRepository;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
	
	private ImageRepository imageRepo ;
	
	private AnswerRepository answerRepo ;
	
	public ImageService(ImageRepository imageRepo, AnswerRepository answerRepo) {
		super();
		this.imageRepo = imageRepo;
		this.answerRepo = answerRepo;
	}

	public void storeFile(MultipartFile multipartFile, Long answerId) throws java.io.IOException {
		// TODO Auto-generated method stub
		Optional<Answers> optionalAnswer = answerRepo.findById(answerId) ;
		
		if(optionalAnswer.isPresent()) {
			String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename())) ;
			Image image = new Image() ;
			image.setName(fileName);
			image.setAnswer(optionalAnswer.get());
			image.setType(multipartFile.getContentType());
			image.setData(multipartFile.getBytes());
			imageRepo.save(image) ;
		}
		else {
			throw new IOException("Answer not found") ;
		}
		
	}
	
	
}
