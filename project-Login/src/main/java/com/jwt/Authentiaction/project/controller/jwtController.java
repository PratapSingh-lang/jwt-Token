package com.jwt.Authentiaction.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.Authentiaction.project.Login.entity.User;
import com.jwt.Authentiaction.project.Login.repo.UserRepo;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class jwtController {

	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/getConnection")
	public ResponseEntity<?> getConnection(){
		return new ResponseEntity<>("this is jwt public Api",HttpStatus.OK);
	}
	
	@GetMapping("/getAdminApi")
	public ResponseEntity<?> getAdminApi(){
		return new ResponseEntity<>("this is jwt Admin Api",HttpStatus.OK);
	}
	
	@GetMapping("/getuserApi")
	public ResponseEntity<?> getuserApi(){
		return new ResponseEntity<>("this is jwt user Api",HttpStatus.OK);
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody User User){
		
		com.jwt.Authentiaction.project.Login.entity.User user2 = this.userRepo.save(User);
		
		return new ResponseEntity<>(user2,HttpStatus.OK);
	}
}
