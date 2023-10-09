package com.douglas.proftechdesk.domain;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import com.douglas.proftechdesk.domain.enums.Profile;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Customer extends Person {

	@Serial
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "customer")
	private List<Ticket> tickets = new ArrayList<>();

	public Customer() {
		super();
		addProfiles(Profile.CUSTOMER);
	}

	public Customer(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
		addProfiles(Profile.CUSTOMER);
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public Customer setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
		return this;
	}
}
