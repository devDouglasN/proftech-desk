package com.douglas.proftechdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.proftechdesk.domain.Ticket;
import com.douglas.proftechdesk.repositories.TicketRepository;
import com.douglas.proftechdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public Ticket findById(Integer id) {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		return ticket.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID:" + id));
	}
}
