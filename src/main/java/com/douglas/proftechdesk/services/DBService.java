package com.douglas.proftechdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.proftechdesk.domain.Customer;
import com.douglas.proftechdesk.domain.Technical;
import com.douglas.proftechdesk.domain.Ticket;
import com.douglas.proftechdesk.domain.enums.Priority;
import com.douglas.proftechdesk.domain.enums.Profile;
import com.douglas.proftechdesk.domain.enums.Status;
import com.douglas.proftechdesk.repositories.PersonRepository;
import com.douglas.proftechdesk.repositories.TicketRepository;

@Service
public class DBService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private TicketRepository ticketRepository;

	public void startDB() {

		Technical tec1 = new Technical(null, "Douglas do Nascimento", "45217854623", "douglas@mail.com", "123");
		tec1.addProfiles(Profile.ADMIN);
		Technical tec2 = new Technical(null, "Marie Curie", "96470567452", "marie@mail.com", "123");
		Technical tec3 = new Technical(null, "Charles Darwin", "37582123751", "darwin@mail.com", "123");
		Technical tec4 = new Technical(null, "Galileo Galilei", "85032652652", "galilei@mail.com", "123");
		Technical tec5 = new Technical(null, "Lionel Messi", "72894660200", "messi@mail.com", "123");
		Technical tec6 = new Technical(null, "Albert Einstein", "64717427890", "einstein@mail.com", "123");

		Customer cus1 = new Customer(null, "Nikola Tesla", "46784861191", "tesla@mail.com", "123");
		Customer cus2 = new Customer(null, "Albert Einstein", "64527493582", "alberteinstein@mail.com", "123");
		Customer cus3 = new Customer(null, "Ada Lovelace", "84197292210", "lovelace@mail.com", "123");
		Customer cus4 = new Customer(null, "Jane Austen", "55816134604", "austen@mail.com", "123");
		Customer cus5 = new Customer(null, "Carl Sagan", "11862280169", "carlsagan@mail.com", "123");
		Customer cus6 = new Customer(null, "Stephen Hawking", "42696747849", "hawking@mail.com", "123");

		Ticket t1 = new Ticket(null, Priority.HIGH, Status.OPENED, "Ticket 1", "Support Request 1", tec1, cus1);
		Ticket t2 = new Ticket(null, Priority.LOW, Status.CLOSED, "Ticket 2", "Support Request 2", tec1, cus3);
		Ticket t3 = new Ticket(null, Priority.LOW, Status.CLOSED, "Ticket 3", "Support Request 3", tec2, cus3);
		Ticket t4 = new Ticket(null, Priority.MEDIUM, Status.PROGRESS, "Ticket 4", "Support Request 4", tec3, cus4);
		Ticket t5 = new Ticket(null, Priority.MEDIUM, Status.OPENED, "Ticket 5", "Support Request 5", tec5, cus5);
		Ticket t6 = new Ticket(null, Priority.HIGH, Status.PROGRESS, "Ticket 6", "Support Request 6", tec6, cus6);

		personRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, tec6, cus1, cus2, cus3, cus4, cus5, cus6));

		ticketRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6));
	}
}
