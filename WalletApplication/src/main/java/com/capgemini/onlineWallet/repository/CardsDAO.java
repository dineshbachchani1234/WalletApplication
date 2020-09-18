package com.capgemini.onlineWallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.onlineWallet.entities.Cards;

public interface CardsDAO extends JpaRepository<Cards,Integer>{
	

}
