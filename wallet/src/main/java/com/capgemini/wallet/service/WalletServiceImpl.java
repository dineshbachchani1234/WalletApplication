package com.capgemini.wallet.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.capgemini.wallet.dao.*;
import com.capgemini.wallet.entities.*;
import com.capgemini.wallet.utility.GlobalResources;


/**
 * @Service annotation is used in your service layer and annotates classes that
 *          perform service tasks, often you don't use it but in many case you
 *          use this annotation to represent a best practice
 */
@Service
public class WalletServiceImpl implements IWalletService {

	/**
	 * Autowires UserDAO object (Dependency Injection)
	 */
	@Autowired
	private UserDAO userDAO;


	private Logger logger = GlobalResources.getLogger(WalletServiceImpl.class);



	@Override
	public String transfer(String mobile, String receiver, double amount, String pin) {

		UserInfo userInfo;

		logger.info("In WalletDAOImpl at function transfer");
		try {
			userInfo = userDAO.getOne(mobile);
			if (mobile.equals(receiver)) {
				logger.error("Self transaction tried by mobile - " + mobile);
				return "Self transfer not Allowed";
			}
			if (userDAO.existsById(receiver)) {
				if (BCrypt.checkpw(pin, userInfo.getPin())) {
					UserInfo receiverUser = userDAO.getOne(receiver);
					if (userInfo.getBalance() >= amount) {
						userInfo.setBalance(userInfo.getBalance() - amount);
						receiverUser.setBalance(amount + receiverUser.getBalance());
						logger.info("Money transfer successful..");

						userInfo.addTransaction(new Transaction(), "Debit",
								amount + " is transfered to " + receiverUser.getMobile(), amount);
						receiverUser.addTransaction(new Transaction(), "Credit",
								amount + "+ is received from " + userInfo.getMobile(), amount);

						userDAO.save(userInfo);
						userDAO.save(receiverUser);

						return "Success! Your Current balance is " + userInfo.getBalance();
					} else {
						logger.error("Insufficient Balance in Wallet of mobile - " + mobile);
						return "Insufficient Balance in Wallet";
					}
				} else {
					logger.error("Incorrect password is entered for mobile - " + mobile);
					return "Enter correct password";
				}
			} else {
				logger.error("Receiver User Doesn't Exists with mobile - " + receiver);
				return "Receiver User Doesn't Exists. Enter correct Receiver Mobile Number";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Transaction> getTransaction(String mobile) {
		UserInfo userInfo;

		logger.info("In WalletDAOImpl at function getTransaction");
		try {
			userInfo = userDAO.getOne(mobile);
			logger.info("User information retrieved by mobile - " + mobile);
			logger.info("All transactions are fetched of mobile - " + mobile);
			System.out.println(userInfo.getTransactions());
			return userInfo.getTransactions();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public String walletToBank(String mobile, long cardNumber, double amount, String pin) {
		try {
			logger.info("In WalletServiceImpl at function walletToBank");
			if (userDAO.existsById(mobile)) {
				UserInfo user = userDAO.getOne(mobile);
				if (BCrypt.checkpw(pin, user.getPin())) {
					if (amount < user.getBalance() && amount > 0) {
						user.addTransactionCard(new Transaction(), "Added Money to Bank",
								amount + " is added to bank account with card number " + cardNumber, amount,
								cardNumber);
						double totalBalance = user.getBalance();
						totalBalance = totalBalance - amount;
						user.setBalance(totalBalance);
						userDAO.save(user);
						return "Success! Your total balance is now " + totalBalance;
					} else {
						return "Invalid amount! Amount should be less than " + user.getBalance();
					}
				} else {
					logger.error(mobile + " this mobile number does not exist");
					return "This mobile number is not registered in our system";
				}
			} else {
				return "Transaction Failed Incorrect Pin";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}


}