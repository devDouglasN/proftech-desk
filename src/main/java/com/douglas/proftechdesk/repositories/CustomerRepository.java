package com.douglas.proftechdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.proftechdesk.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
