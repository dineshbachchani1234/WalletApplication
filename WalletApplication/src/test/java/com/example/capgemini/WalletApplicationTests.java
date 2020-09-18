package com.example.capgemini;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.onlineWallet.controller.WalletController;
import com.capgemini.onlineWallet.service.IWalletService;
import com.capgemini.onlineWallet.utility.GlobalResources;

@SpringBootTest
class WalletApplicationTests {

	@Test
	void contextLoads() {
	}
	
private static Logger Logger= GlobalResources.getLogger(WalletApplicationTests.class);
	
	@Autowired
	IWalletService service;
	
	WalletController controller;

	@BeforeAll
	static void setUpBeforeClass()
	{
			
		Logger.info("SetUpClass called");
	}
	
	@BeforeEach
	void setup()
	{
		Logger.info("Test Case Started");

	System.out.println("Test Case Started");
	}
	
	@AfterEach
	void tearDown()
	{
		Logger.info("Test Case Over");
		System.out.println("Test Case Over");
	}
	

}
