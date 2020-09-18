package com.capgemini.onlineWallet.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "t")
public class Transaction{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String statement;
	private Date timeOfTransaction;
	private String transactionType;
	
	@ManyToOne
	@JoinColumn(name="mobile")
	private UserInfo userInfo;
	
	
	public  Transaction(String statement) 
	{
		super();
		this.statement = statement;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public Date getTimeOfTransaction() {
		return timeOfTransaction;
	}
	public void setTimeOfTransaction(Date timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Transaction(int id, String statement, Date timeOfTransaction, String transactionType) {
		super();
		this.id = id;
		this.statement = statement;
		this.timeOfTransaction = timeOfTransaction;
		this.transactionType = transactionType;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
