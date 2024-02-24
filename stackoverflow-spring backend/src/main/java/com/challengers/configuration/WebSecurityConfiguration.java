package com.challengers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.challengers.services.jwt.UserDetailsServiceClass;
import com.challengers.services.questions.QuestionService;
import com.challengers.services.questions.QuestionServiceClass;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		return security
				.csrf()
				.disable()
				.authorizeHttpRequests()
				.requestMatchers("/login","/register")
				.permitAll()
				.and()
				.authorizeHttpRequests() 
				.requestMatchers("/api/**")
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.build() ;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ;  
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	 @Bean
	 public UserDetailsService userDetailsService() {
	    return new UserDetailsServiceClass(); // Or inject an instance if it requires dependencies
	 }
	 
	 @Bean
	 public QuestionService questionService() {
	    return new QuestionServiceClass(null, null); // Or inject an instance if it requires dependencies
	 }
}
