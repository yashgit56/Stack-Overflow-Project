package com.challengers.controllers;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challengers.dtos.AuthenticationDTO;
import com.challengers.dtos.AuthenticationResponse;
import com.challengers.entities.User;
import com.challengers.repositories.UserRepository;
import com.challengers.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController

public class AuthenticationController {
	
	@Autowired
	public AuthenticationManager authenticationManager ;
	
	@Autowired
	private UserDetailsService userDetailsService ;
	
	@Autowired
	private UserRepository userRepo ;
	
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	
	@Autowired
	private JwtUtil jwtutil ;
	
	@PostMapping("/login")
	public void createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response) throws IOException, JSONException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),authenticationDTO.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect Email or Password") ;
		}
		catch(DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created");
			return;
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getEmail());
		
		Optional<User> optionalUser = userRepo.findFirstByEmail(userDetails.getUsername()) ;
		
		final String jwt = jwtutil.generateToken(userDetails);
		
		if(optionalUser.isPresent()) {
			response.getWriter().write(new JSONObject()
					.put("userId",optionalUser.get().getId())
					.toString()
			);
		}
		
		response.addHeader("Access-Control-Expose-Headers", "Authorization") ;
		response.setHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, X-Requested-With, Content-Type, Accept, X-Custom-header");
		response.setHeader(HEADER_STRING, TOKEN_PREFIX);
		
	}
}
