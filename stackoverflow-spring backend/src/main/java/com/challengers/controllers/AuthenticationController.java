package com.challengers.controllers;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.challengers.entities.User;
import com.challengers.repositories.UserRepository;

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
    
    
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),authenticationDTO.getPassword()));
        }
        catch(BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Email or Password");
        }
        catch(DisabledException disabledException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not created");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getEmail());
        
        Optional<User> optionalUser = userRepo.findFirstByEmail(userDetails.getUsername()) ;
        
        
        if(optionalUser.isPresent()) {
            return ResponseEntity.ok(new JSONObject()
                .put("authenticated", true)
                .put("userId", optionalUser.get().getId())
                .toString()
            );
        } else {
            return ResponseEntity.ok(new JSONObject()
                .put("authenticated", false)
                .toString()
            );
        }
    }
}
