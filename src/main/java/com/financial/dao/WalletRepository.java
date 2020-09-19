package com.financial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financial.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

}
