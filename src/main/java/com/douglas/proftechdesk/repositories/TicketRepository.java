package com.douglas.proftechdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.proftechdesk.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
