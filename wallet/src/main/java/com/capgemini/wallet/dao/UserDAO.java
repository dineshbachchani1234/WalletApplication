package com.capgemini.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.wallet.entities.UserInfo;

/**
 * This is UserInfo Repository
 * @Repository is a Spring annotation that indicates that the decorated class 
 * is a repository. A repository is a mechanism for encapsulating storage, 
 * retrieval, and search behavior which emulates a collection of objects.
 * A repository contains methods for performing CRUD operations, sorting and paginating data
 */
@Repository
public interface UserDAO extends JpaRepository<UserInfo,String>{
	
	UserInfo findByUsername(String username);
	UserInfo findByAadhar(long aadhar);
	UserInfo findByMobile(String mobile);

	boolean existsByUsername(String username);
	boolean existsByAadhar(long aadhar);
	boolean existsByMobile(String mobile);
}
