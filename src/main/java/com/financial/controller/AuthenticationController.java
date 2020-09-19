package com.financial.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.dao.RoleRepository;
import com.financial.dao.UserRepository;
import com.financial.entity.ERole;
import com.financial.entity.Role;
import com.financial.entity.User;
import com.financial.entity.Wallet;
import com.financial.jwt.JwtUtils;
import com.financial.model.JwtResponse;
import com.financial.model.LoginRequest;
import com.financial.model.MesageResponse;
import com.financial.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
		
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwt(authentication);
		UserDetailsImpl detailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		return ResponseEntity.ok(new JwtResponse(token, detailsImpl.getId(), detailsImpl.getFristname(), detailsImpl.getLastname(), 
				detailsImpl.getMobile(), detailsImpl.getUsername(),
				detailsImpl.getEmail(),detailsImpl.getAuthorities())) ;
		
	}
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody User registrationRequest){
		
		if(userRepository.existsByUsername(registrationRequest.getUsername()) ) {
			return ResponseEntity.badRequest().body(new MesageResponse("Error: Username is already taken!"));
		}
		
		if(userRepository.existsByEmail(registrationRequest.getEmail()) ) {
			return ResponseEntity.badRequest().body(new MesageResponse("Error: email is already taken!"));
		}
		
		 User user = new User(registrationRequest.getFristname(),registrationRequest.getLastname(),registrationRequest.getMobile()
				 ,registrationRequest.getUsername(), registrationRequest.getEmail(), passwordEncoder.encode(registrationRequest.getPassword()));
		 

		 Set<Role> roles = new HashSet<>();
		 Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		 roles.add(userRole);
		 user.setRoles(roles);
		 
		 Wallet wallet = new Wallet(0);		 
		 user.setWallet(wallet);
		 
		 userRepository.save(user);

		return ResponseEntity.ok("registerd");
	}
}
