package com.capgemini.onlineWallet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlineWallet.entities.Transaction;
import com.capgemini.onlineWallet.repository.CardsDAO;
import com.capgemini.onlineWallet.repository.TransactionsDAO;
import com.capgemini.onlineWallet.service.IWalletService;
import com.capgemini.onlineWallet.utility.GlobalResources;

@RestController
@RequestMapping("/")
//@CrossOrigin(origins="*")
public class WalletController {

	
	@Autowired
	private IWalletService service;
	
	private Logger logger=GlobalResources.getLogger(WalletController.class);
	
	@PutMapping(value = "/transfer/{sender}/{receiver}/{amount}/{password}")
	public String transfer(@PathVariable("sender")String mobile,@PathVariable("receiver")String receiver,
			@PathVariable("amount")double amount, @PathVariable("password")String password)
	{
		logger.info("In WalletController at function transfer");
		return service.transfer(mobile, receiver, amount, password);		
	}
	
	@GetMapping(value = "/getTransactions/{mobileNumber}")
	public List<Transaction> getTransaction(@PathVariable("mobileNumber")String mobile)
	{
		logger.info("In WalletController at function getTransaction");
		return service.getTransaction(mobile);
	}
}
	
