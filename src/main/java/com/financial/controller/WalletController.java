package com.financial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.dao.UserRepository;
import com.financial.dao.WalletRepository;
import com.financial.entity.User;
import com.financial.entity.Wallet;
import com.financial.model.WalletRespons;

@RestController
@RequestMapping("/api/financial")
public class WalletController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@GetMapping("/getWallet/{userId}")
	public ResponseEntity<?> getWallet(@PathVariable ("userId") int userId){
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		
		Wallet wallet = user.getWallet();
		
		
		return ResponseEntity.ok(new WalletRespons(wallet.getId(), wallet.getBalance())) ;
	}

	@PutMapping("/addToWallet/{walletId}")
	public ResponseEntity<?> addToWallet(@PathVariable ("walletId") int walletId,@RequestBody Wallet wallet){
		
		Wallet theWallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		
		theWallet.setBalance(theWallet.getBalance() + wallet.getBalance());
		
		walletRepository.save(theWallet);
		
		return ResponseEntity.ok("DONE");
	}
	
}
