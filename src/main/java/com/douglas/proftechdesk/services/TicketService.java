package com.douglas.proftechdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.proftechdesk.domain.Customer;
import com.douglas.proftechdesk.domain.Technical;
import com.douglas.proftechdesk.domain.Ticket;
import com.douglas.proftechdesk.domain.dtos.TicketDTO;
import com.douglas.proftechdesk.domain.enums.Priority;
import com.douglas.proftechdesk.domain.enums.Status;
import com.douglas.proftechdesk.repositories.TicketRepository;
import com.douglas.proftechdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TechnicalService technicalService;

	@Autowired
	private CustomerService customerService;

	public Ticket findById(Integer id) {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		return ticket.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID: " + id));
	}

	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	public Ticket create(@Valid TicketDTO ticketDTO) {
		return ticketRepository.save(newTicket(ticketDTO));
	}

	public Ticket update(Integer id, TicketDTO ticketDTO) {
		ticketDTO.setId(id);
		Ticket oldTicket = findById(id);
		oldTicket = newTicket(ticketDTO);
		return ticketRepository.save(oldTicket);
	}

	private Ticket newTicket(TicketDTO ticketDTO) {
		Technical technical = technicalService.findById(ticketDTO.getTechnical());
		Customer customer = customerService.findById(ticketDTO.getCustomer());

		Ticket ticket = new Ticket();
		if (ticketDTO.getId() != null) {
			ticket.setId(ticketDTO.getId());
		}
		if (ticketDTO.getStatus().equals(2)) {
			ticket.setClosingDate(LocalDate.now());
		}

		ticket.setTechnical(technical);
		ticket.setCustomer(customer);
		ticket.setPriority(Priority.toEnum(ticketDTO.getPriority()));
		ticket.setStatus(Status.toEnum(ticketDTO.getStatus()));
		ticket.setTitle(ticketDTO.getTitle());
		ticket.setObservations(ticketDTO.getObservations());
		return ticket;
	}
}
