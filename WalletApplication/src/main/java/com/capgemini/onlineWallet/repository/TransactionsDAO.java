package com.capgemini.onlineWallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.onlineWallet.entities.Transaction;

public interface TransactionsDAO extends JpaRepository<Transaction,Integer>{
	

}
