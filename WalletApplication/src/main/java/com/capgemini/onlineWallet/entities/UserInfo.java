package com.capgemini.onlineWallet.entities;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name="u")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(length = 10)
	private String mobile;
	@Column(length = 10)
	private String firstName;
	@Column(length = 10)
	private String lastName;
	@Column(length = 10)
	private String username;
	@Column(length = 10)
	private String password;
	@Column(length = 10)
	private String role;
	@Column(length = 10)
	private String status;
	@Column(length = 10)
	private double balance;
	@Column(length = 12)
	private long aadhar;
	@Column(length = 10)
	private String securityQ;
	@Column(length = 10)
	private String securityA;
	
	
	
	@JsonBackReference(value="Transaction")
	@OneToMany(mappedBy="userInfo",cascade=CascadeType.ALL)
	private List<Transaction> transactions=new ArrayList<>();
	
	 @JsonBackReference(value="Cards")
	 @JsonIgnoreProperties
	@OneToMany(mappedBy="userInfo",cascade=CascadeType.ALL)
	private List<Cards> cards=new ArrayList<>();
	
	
	
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public List<Cards> getCards() {
		return cards;
	}
	public void setCards(List<Cards> cards) {
		this.cards = cards;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile() {
		return mobile;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getAadhar() {
		return aadhar;
	}
	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}
	public String getSecurityQ() {
		return securityQ;
	}
	public void setSecurityQ(String securityQ) {
		this.securityQ = securityQ;
	}
	public String getSecurityA() {
		return securityA;
	}
	public void setSecurityA(String securityA) {
		this.securityA = securityA;
	}
	public UserInfo()
	{
		super();
	}
	
	public void addTransaction(Transaction transaction) 
	{
		transaction.setUserInfo(this);
		transaction.setTimeOfTransaction(Calendar.getInstance().getTime());
//		transaction.setTransactionDate(Calendar.getInstance().getTime());
		this.getTransactions().add(transaction);
	}
}
