package com.financial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.financial.entity.Wallet;

@RepositoryRestResource(exported=false)
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

}
