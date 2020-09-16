package com.financial.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.jwt.JwtUtils;
import com.financial.model.JwtResponse;
import com.financial.model.LoginRequest;
import com.financial.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		String token = jwtUtils.generateJwt(authentication);
		UserDetailsImpl detailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		return ResponseEntity.ok(new JwtResponse(token, detailsImpl.getId(), detailsImpl.getFristname(), detailsImpl.getLastname(), 
				detailsImpl.getMobile(), detailsImpl.getUsername(),
				detailsImpl.getEmail(),detailsImpl.getAuthorities())) ;
		
	}

}
