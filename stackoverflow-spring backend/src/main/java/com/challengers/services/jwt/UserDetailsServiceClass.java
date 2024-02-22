package com.challengers.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.challengers.entities.User;
import com.challengers.repositories.UserRepository;

public class UserDetailsServiceClass implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo ;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepo.findFirstByEmail(email) ;
		if(userOptional.isEmpty())
			throw new UsernameNotFoundException("User not found");
		return new org.springframework.security.core.userdetails.User(userOptional.get().getEmail(), userOptional.get().getPassword(), new ArrayList<>());
	}
	
}
