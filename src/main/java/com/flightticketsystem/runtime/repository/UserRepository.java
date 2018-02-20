package com.flightticketsystem.runtime.repository;

import com.flightticketsystem.runtime.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNameOrUserEmail(String userName, String userEmail);

    User findByUserName(String userName);

    User findByUserEmail(String email);

    User findUserByAccountOwner_PersonId(Integer personId);

}