package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bean.Admin;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
    Optional<Admin> findByUsername(String username);
}
