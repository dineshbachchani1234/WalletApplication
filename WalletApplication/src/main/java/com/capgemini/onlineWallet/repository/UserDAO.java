package com.capgemini.onlineWallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.onlineWallet.entities.UserInfo;



public interface UserDAO extends JpaRepository<UserInfo, String> 
{

}
