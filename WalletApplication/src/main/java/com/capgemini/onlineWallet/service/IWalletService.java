package com.capgemini.onlineWallet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onlineWallet.entities.Transaction;



public interface IWalletService {

	public String transfer(String mobile,String receiver,double amount,String password);
	
	public List<Transaction> getTransaction(String mobile);

}
