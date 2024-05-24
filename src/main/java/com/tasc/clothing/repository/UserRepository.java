package com.tasc.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasc.clothing.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
