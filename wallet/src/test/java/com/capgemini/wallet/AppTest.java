package com.capgemini.wallet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.wallet.controller.WalletController;
import com.capgemini.wallet.service.IWalletService;
import com.capgemini.wallet.utility.GlobalResources;



/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest 
{
	@Test
	void contextLoads() {
	}
	private static Logger Logger= GlobalResources.getLogger(AppTest.class);

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
