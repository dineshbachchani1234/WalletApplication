package com.capgemini.wallet.service;

import java.util.List;


import com.capgemini.wallet.entities.*;

/**
 * This is service layer interface with contains declaration of all the functions.
 *
 */
public interface IWalletService {
	
	
	
	public String walletToBank(String mobile,long cardNumber,double amount,String pin);
	
	public String transfer(String mobile, String receiver, double amount, String pin);

	public List<Transaction> getTransaction(String mobile);
	
	
}
