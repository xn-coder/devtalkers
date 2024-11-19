package com.xncoder.devtalker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xncoder.devtalker.Models.User;

@Repository
public interface AuthenticationRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}