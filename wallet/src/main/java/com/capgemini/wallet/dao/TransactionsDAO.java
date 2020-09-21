package com.capgemini.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.wallet.entities.Transaction;

/**
 * This is Transaction Repository
 * @Repository is a Spring annotation that indicates that the decorated class 
 * is a repository. A repository is a mechanism for encapsulating storage, 
 * retrieval, and search behavior which emulates a collection of objects.
 */
@Repository
public interface TransactionsDAO extends JpaRepository<Transaction,Integer>{
	

}
