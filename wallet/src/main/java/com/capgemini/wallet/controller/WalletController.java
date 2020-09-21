package com.capgemini.wallet.controller;

import java.util.List;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.wallet.entities.Transaction;
import com.capgemini.wallet.service.IWalletService;
import com.capgemini.wallet.utility.GlobalResources;

/**
 * @author Sumeet Patil
 * 		   Date of creation: 14/09/2020 
 * 		   This is the controller for managing services related to Wallet.
 * 		   This controller maps the requests coming from front end to back end.
 */

/**
 * 	The @RestController annotation was introduced in Spring 4.0 to simplify the creation of
 *  RESTful web services. It's a convenience annotation that combines @Controller and 
 *  @ResponseBody – which eliminates the need to annotate every request handling method of
 *  the controller class with the @ResponseBody annotation.
 */
@RestController

/**
 * The annotation in Spring Framework is used for mapping a particular HTTP request method to
 *  a specific class/ method in the controller that will be handling the respective request. 
 *  This annotation can be applied at both levels: Class level: Maps the URL of the request
 */
@RequestMapping("/")

/**
 *	Cross-Origin Resource Sharing (CORS) is a security concept that allows restricting the 
 *	resources implemented in web browsers. It prevents the JavaScript code producing or 
 *	consuming the requests against different origin
 */
@CrossOrigin(origins="*")
public class WalletController {

	/**
	 *  Autowires IWalletService object (Dependency Injection)
	 */
	@Autowired
	private IWalletService service;
	

	
	
	private Logger logger=GlobalResources.getLogger(WalletController.class);
	
	
	/**
	 * This method is used to Transfer funds from one user wallet to other
	 * @param request :  HttpServletRequest
	 * @param receiver :  String
	 * @param amount :  double
	 * @param pin :  pin
	 * @return String
	 * 
	 */
	@PutMapping(value = "/transfer/{mobile}/{receiver}/{amount}/{pin}")
	public String transfer(@PathVariable String mobile,@PathVariable String receiver,
			@PathVariable double amount, @PathVariable String pin)
	{

		logger.info("In WalletController at function transfer");
		return service.transfer(mobile, receiver, amount, pin);		
	}
	
	/**
	 * This method is used transfer back to bank from wallet
	 * @param request :  HttpServletRequest
	 * @param cardNumber :  long
	 * @param amount :  double
	 * @return String
	 * 
	 */
	@PutMapping("/withdrawMoney/{mobile}/{cardNumber}/{amount}/{pin}")
	public String walletToBank(@PathVariable String mobile, @PathVariable long cardNumber, @PathVariable double amount, @PathVariable String pin)
	{   
		logger.info("In WalletController at function addMoney");

		return service.walletToBank(mobile,cardNumber,amount,pin);
		
	}
	
	/**
	 * This method is used get List of all transactions
	 * @param request :  HttpServletRequest
	 * @return String
	 * 
	 */
	@GetMapping(value = "/getTransactions/{mobile")
	public List<Transaction> getTransaction(@PathVariable String mobile)
	{

		
		logger.info("In WalletController at function getTransaction");
		return service.getTransaction(mobile);
	}

}
	
