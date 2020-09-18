package com.capgemini.onlineWallet.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlineWallet.entities.Transaction;
import com.capgemini.onlineWallet.entities.UserInfo;
import com.capgemini.onlineWallet.repository.UserDAO;

@Service
public class WalletServiceImpl implements IWalletService {

private Logger logger = Logger.getRootLogger();
	
	@Autowired
	UserDAO userRepo;
	
	UserInfo userInfo;
	
	@Override
	public String transfer(String mobile, String receiver, double amount, String password) 
	{
		logger.info("In WalletDAOImpl at function transfer");

			userInfo=userRepo.getOne(mobile);
			if(mobile.equals(receiver)) {
				logger.error("Self transaction tried by mobile - "+mobile);
				return "Self transfer not Allowed";
			}
			if(userRepo.findById(receiver) != null) 
			{
				if(userInfo.getPassword().equals(password)) 
				{
					UserInfo receiverUser=userRepo.getOne(receiver);
					if(userInfo.getBalance()>=amount) 
					{
						userInfo.setBalance(userInfo.getBalance()-amount);
						receiverUser.setBalance(amount+receiverUser.getBalance());
						logger.info("Money transfer successful..");
						userInfo.addTransaction(new Transaction(amount+"- is transferred to "+receiverUser.getFirstName() +"/"+receiverUser.getMobile()));
						receiverUser.addTransaction(new Transaction(amount+"+ is received from "+userInfo.getFirstName()+"/"+userInfo.getMobile()));
						logger.info("New Transaction for mobile - "+mobile+" = "+amount+"- is transferred to "+receiverUser.getFirstName()+"/"+receiverUser.getMobile());
						logger.info("New Transaction for mobile - "+receiver +"= "+amount+"+ is received from "+userInfo.getFirstName()+"/"+userInfo.getMobile());
						
						userRepo.save(userInfo);
						userRepo.save(receiverUser);
						
						return "ok";
					}
					else {
						logger.error("Insufficient Balance in Wallet of mobile - "+mobile);
						return "Insufficient Balance in Wallet";
					}
				}
				else {
					logger.error("Incorrect password is entered for mobile - "+mobile);
					return "Enter correct password";
				}
			}
			else {
				logger.error("Receiver User Doesn't Exists with mobile - "+receiver);
				return "Receiver User Doesn't Exists. Enter correct Receiver Mobile Number";
			}
	
	
	}

	@Override
	public List<Transaction> getTransaction(String mobile) 
	{
		logger.info("In WalletDAOImpl at function getTransaction");
		
			userInfo=userRepo.getOne(mobile);
			System.out.println(userInfo.getFirstName());
			logger.info("User information retrieved by mobile - "+mobile);
			logger.info("All transactions are fetched of mobile - "+mobile);
			return userInfo.getTransactions();
		
	}

}
