package com.flightticketsystem.runtime.repository;

import com.flightticketsystem.runtime.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserNameOrEmail(String userName, String userEmail);
}