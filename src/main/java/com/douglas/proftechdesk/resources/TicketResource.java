package com.douglas.proftechdesk.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.proftechdesk.domain.Ticket;
import com.douglas.proftechdesk.domain.dtos.TicketDTO;
import com.douglas.proftechdesk.services.TicketService;

@RestController
@RequestMapping(value = "/tickets")
public class TicketResource {

	@Autowired
	private TicketService ticketService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TicketDTO> findById(@PathVariable Integer id) {
		Ticket ticket = ticketService.findById(id);
		return ResponseEntity.ok().body(new TicketDTO(ticket));
	}

	@GetMapping
	public ResponseEntity<List<TicketDTO>> findAll() {
		List<Ticket> list = ticketService.findAll();
		List<TicketDTO> listDTO = list.stream().map(TicketDTO::new).toList();
		return ResponseEntity.ok().body(listDTO);
	}
}
