package com.douglas.proftechdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.proftechdesk.domain.Person;

public interface  PersonRepository extends JpaRepository<Person, Integer> {
}
