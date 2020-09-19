package com.financial.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.dao.TransactionRepository;
import com.financial.dao.WalletRepository;
import com.financial.entity.Transaction;
import com.financial.entity.Wallet;
import com.financial.model.TransactionRespons;

@RestController
@RequestMapping("/api/financial")
public class TransactionController {
	
	@Autowired 
	private WalletRepository walletRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;


	@GetMapping("/transactions/{walletId}")
	public ResponseEntity<?> getTransations(@PathVariable("walletId") int walletId){
		
		Wallet wallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		
		Set<Transaction> transactions = wallet.getTransaction();
		
		Set<TransactionRespons> transactionRespons = new HashSet<>();
		
		for(Transaction tempTransaction : transactions ) {
			transactionRespons.add(new TransactionRespons(tempTransaction.getId(),tempTransaction.getAmount()));
		}
		
		return ResponseEntity.ok(transactionRespons);
	}
	
	@PostMapping("/transactions/{walletId}")
	public ResponseEntity<?> ProcessTransaction(@PathVariable("walletId") int walletId
			, @RequestBody Transaction transaction){
		
		Transaction tempTransaction = new Transaction(transaction.getAmount());
				
		Wallet wallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		
		wallet.setBalance(wallet.getBalance() - tempTransaction.getAmount());
		
		tempTransaction.setWallet(wallet);
				
		transactionRepository.save(tempTransaction);
		
		walletRepository.save(wallet);
		
		return ResponseEntity.ok("DOne");
	}
	
	
}
