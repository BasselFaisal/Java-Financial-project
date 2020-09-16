package com.financial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.financial.entity.Role;

/*@RepositoryRestResource(exported=false)
*/public interface RoleRepository extends JpaRepository<Role, Integer> {

}
