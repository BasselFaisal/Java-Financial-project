package com.financial.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="wallet")
public class Wallet {
	
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
		
		private double balance;
		
		@OneToOne(fetch = FetchType.LAZY)
	    @JoinTable(name = "user_wallet",joinColumns = @JoinColumn(name = "wallet_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
		private User user;
		
		@OneToMany(fetch = FetchType.LAZY)
		@JoinTable(name = "transaction_wallet", joinColumns = @JoinColumn(name="wallet_id"),inverseJoinColumns = @JoinColumn(name="transaction_id"))
		private Set<Transaction> transaction;

		public Wallet(int id, double balance, User user, Set<Transaction> transaction) {
			this.id = id;
			this.balance = balance;
			this.user = user;
			this.transaction=transaction;
		}

		public Wallet() {
		}

		public Wallet(double balance) {
			this.balance = balance;
		}

		public int getId() {
			return id;
		}

		public double getBalance() {
			return balance;
		}

		public User getUser() {
			return user;
		}

		public Set<Transaction> getTransaction() {
			return transaction;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public void setTransaction(Set<Transaction> transaction) {
			this.transaction = transaction;
		}
	
}
