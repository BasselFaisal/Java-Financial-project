package com.financial.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.financial.entity.Role;
import com.financial.entity.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String fristname;
	
	private String lastname;
		
	private String mobile;
		
	private String username;
	
	private String email;

	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> getAuthorities;
	
	
	
	public UserDetailsImpl(int id, String fristname, String lastname, String mobile, String username, String email,
			String password, Collection<? extends GrantedAuthority> getAuthorities) {
		this.id = id;
		this.fristname = fristname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.username = username;
		this.email = email;
		this.password = password;
		this.getAuthorities = getAuthorities;
	}
	
	public static UserDetailsImpl build(User user) {
		
		 Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        for (Role role : user.getRoles()){
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().name()));
	        }
		
		return new UserDetailsImpl(user.getId(),
									user.getFristname(),
									user.getLastname(),
									user.getMobile(),
									user.getUsername(),									
									user.getEmail(),
									user.getPassword(),
									grantedAuthorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getAuthorities;
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

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
