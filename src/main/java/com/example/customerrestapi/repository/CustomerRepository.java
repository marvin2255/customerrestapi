package com.example.customerrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customerrestapi.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

