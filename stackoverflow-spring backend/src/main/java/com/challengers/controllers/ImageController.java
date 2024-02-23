package com.challengers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.challengers.services.user.image.ImageService;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {
	
	private final ImageService imageService ;
	
	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}

	@PostMapping("/image/answerId")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile multipartFile, @PathVariable Long answerId) throws java.io.IOException{
		try {
			imageService.storeFile(multipartFile,answerId) ;
			return ResponseEntity.ok("Image Stored Successfully") ;
		}
		catch(IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}
