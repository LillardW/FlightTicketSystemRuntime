package com.flightticketsystem.runtime.dao;

import com.flightticketsystem.runtime.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Customer, Long>{
    public Customer findByCustomerName(String customerName);

    public Customer save(Customer customer);


}
