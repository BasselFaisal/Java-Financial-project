package com.financial.jwt;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.financial.service.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${financial.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	@Value("${financial.app.jwtSecret}")
	 private String secretKey;

	
	public String generateJwt(Authentication authentication) {
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userDetailsImpl.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}


	public boolean validateJwtToken(String jwt) {
				try {
				Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
				return true;
			}catch (SignatureException e) {
				logger.error("Invalid JWT signature: {}", e.getMessage());
			}
				return true;

		}

	public String getUserNameFromJwtToken(String jwt) {
			return 	Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody().getSubject();

		}
}
