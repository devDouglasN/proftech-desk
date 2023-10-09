package com.douglas.proftechdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.proftechdesk.domain.Technical;

public interface TechnicalRepository extends JpaRepository<Technical, Integer> {
}
