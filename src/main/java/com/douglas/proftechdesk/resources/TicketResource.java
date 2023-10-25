package com.douglas.proftechdesk.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.proftechdesk.domain.Ticket;
import com.douglas.proftechdesk.domain.dtos.TicketDTO;
import com.douglas.proftechdesk.services.TicketService;

import jakarta.validation.Valid;

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

	@PostMapping
	public ResponseEntity<TicketDTO> create(@Valid @RequestBody TicketDTO ticketDTO) {
		Ticket ticket = ticketService.create(ticketDTO);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequestUri().path("/{id}").buildAndExpand(ticket.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
