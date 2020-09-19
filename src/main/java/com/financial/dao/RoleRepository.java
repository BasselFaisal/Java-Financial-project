package com.financial.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.financial.entity.ERole;
import com.financial.entity.Role;

@RepositoryRestResource(exported=false)
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(ERole roleAdmin);

}
