package com.challengers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengers.dtos.RegisterDTO;
import com.challengers.dtos.UserDTO;
import com.challengers.services.user.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService ;
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody(required=true) RegisterDTO registerDTO) {
		
		if(userService.hasUserWithEmail(registerDTO.getEmail())) {
			return new ResponseEntity<>("User already exist with this email" + registerDTO.getEmail(),HttpStatus.NOT_ACCEPTABLE) ;
		}
		
		UserDTO createdUser = userService.createUser(registerDTO);
		if(createdUser == null) {
			return new ResponseEntity<>("User not created",HttpStatus.BAD_REQUEST) ;
		}
		else {
			return new ResponseEntity<>(createdUser, HttpStatus.CREATED) ;
		}
	}

}
