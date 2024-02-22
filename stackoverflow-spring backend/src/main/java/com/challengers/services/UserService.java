package com.challengers.services;

import com.challengers.dtos.RegisterDTO;
import com.challengers.dtos.UserDTO;

public interface UserService {
	
	UserDTO createUser(RegisterDTO registerDTO);
	
	boolean hasUserWithEmail(String email) ;
	
}
