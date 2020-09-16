package com.financial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.financial.entity.User;

@RepositoryRestResource(exported=false)
public interface UserRepositiry extends JpaRepository<User, Integer> {
	
	public User findUserByUsername(String username);

}
