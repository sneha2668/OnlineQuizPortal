package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.User;

@Repository
public interface Userrepo extends JpaRepository<User, Integer>{
	
	 public User findByEmailid(String emailid);

	public static Optional<User> findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
