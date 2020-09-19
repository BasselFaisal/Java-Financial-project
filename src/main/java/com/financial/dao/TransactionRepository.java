package com.financial.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.financial.entity.Transaction;

@RepositoryRestResource(exported=false)
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
