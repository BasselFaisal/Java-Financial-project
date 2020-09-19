package com.financial.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/*
	 * @NotBlank private LocalDateTime transactiondate;
	 */
	
	private double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "transaction_wallet", joinColumns = @JoinColumn(name="transaction_id"),inverseJoinColumns = @JoinColumn(name="wallet_id"))
	private Wallet wallet;

	public Transaction() {
	}

	public Transaction(@NotBlank double amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

}
