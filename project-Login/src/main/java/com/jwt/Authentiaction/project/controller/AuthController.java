package com.jwt.Authentiaction.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.Authentiaction.project.Login.entity.User;
import com.jwt.Authentiaction.project.Login.security.JwtAuthenticationFilter;
import com.jwt.Authentiaction.project.Login.security.JwtTokenHelper;
import com.jwt.Authentiaction.project.payloads.JwtAuthRequest;
import com.jwt.Authentiaction.project.payloads.JwtAuthResponse;
import com.jwt.Authentiaction.project.payloads.UserDto;


@RestController
@RequestMapping("/api/v1/auth/")
@Component
public class AuthController {
	
	@Autowired(required=true)
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	

	@Autowired
	private UserDetailsService userService;
//	@Autowired
//	private UserRepository userRepo;
	@Autowired
	private ModelMapper mapper;
	
	Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	/**
	 * login Api
	 * */
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.mapper.map((User) userDetails, UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}
	
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			log.info("Invalid Detials !!");
			throw new Exception("Invalid username or password !!");
		}
		
	}
}
