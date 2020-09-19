package com.financial.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String fristname;
	
	@NotBlank
	private String lastname;
	
	@NotBlank
	private String mobile;
	
	@NotBlank
	private String username;

	@NotBlank
	private String email;

	@NotBlank
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_wallet",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "wallet_id"))
	private Wallet wallet;
	
	private boolean enable;
	
	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns =@JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	public User() {
	}

	public User(@NotBlank String fristname, @NotBlank String lastname, @NotBlank String mobile,
			@NotBlank String username, @NotBlank String email, @NotBlank String password, Wallet wallet,
			Set<Role> roles) {
		this.fristname = fristname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.username = username;
		this.email = email;
		this.password = password;
	}



	public User(@NotBlank String fristname, @NotBlank String lastname, @NotBlank String mobile,
			@NotBlank String username, @NotBlank String email, @NotBlank String password) {
		this.fristname = fristname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getFristname() {
		return fristname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getMobile() {
		return mobile;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public boolean isEnable() {
		return enable;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFristname(String fristname) {
		this.fristname = fristname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
