package com.financial.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.financial.entity.Role;

public class JwtResponse {
	
	private String token;
	private int id;
	private String fristname;
	private String lastname;
	private int mobile;
	private String username;
	private String email;
	private Collection<? extends GrantedAuthority> getAuthorities;
	
	
	public JwtResponse(String token, int id, String fristname, String lastname, int mobile, String username,
			String email, Collection<? extends GrantedAuthority> getAuthorities) {
		this.token = token;
		this.id = id;
		this.fristname = fristname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.username = username;
		this.email = email;
		this.getAuthorities=getAuthorities;
	}
	public String getToken() {
		return token;
	}
	public int getId() {
		return id;
	}
	public String getFristname() {
		return fristname;
	}
	public String getLastname() {
		return lastname;
	}
	public int getMobile() {
		return mobile;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	
	public Collection<? extends GrantedAuthority> getGetAuthorities() {
		return getAuthorities;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFristname(String fristname) {
		this.fristname = fristname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setGetAuthorities(Collection<? extends GrantedAuthority> getAuthorities) {
		this.getAuthorities = getAuthorities;
	}
}
