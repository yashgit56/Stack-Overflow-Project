package com.challengers.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.challengers.dtos.RegisterDTO;
import com.challengers.dtos.UserDTO;
import com.challengers.entities.User;
import com.challengers.repositories.UserRepository;

@Service
public class UserServiceClass implements UserService {
	
	@Autowired
	private UserRepository userRepo ;
	
	public UserDTO createUser(RegisterDTO registerDTO) {
		User user = new User() ;
		user.setEmail(registerDTO.getEmail());
		user.setUsername(registerDTO.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode(registerDTO.getPassword()));
		User createdUser = userRepo.save(user) ;
		UserDTO createdUserDTO = new UserDTO() ;
		createdUserDTO.setId(createdUser.getId());
		return createdUserDTO ;
	}

	@Override
	public boolean hasUserWithEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findFirstByEmail(email).isPresent();
	}

	
}