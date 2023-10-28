package com.douglas.proftechdesk.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void startDB() {

		Technical tec1 = new Technical(null, "Douglas do Nascimento", "965.459.470-60", "douglas@mail.com", encoder.encode("123"));
		tec1.addProfiles(Profile.ADMIN);

		Technical tec2 = new Technical(null, "Marie Curie", "855.624.530-70", "marie@mail.com", encoder.encode("123"));
		Technical tec3 = new Technical(null, "Charles Darwin", "618.826.730-77", "darwin@mail.com", encoder.encode("123"));
		Technical tec4 = new Technical(null, "Galileo Galilei", "626.908.050-90", "galilei@mail.com", encoder.encode("123"));
		Technical tec5 = new Technical(null, "Lionel Messi", "483.330.970-06", "messi@mail.com", encoder.encode("123"));
		Technical tec6 = new Technical(null, "Albert Einstein", "996.738.980-06", "einstein@mail.com", encoder.encode("123"));
		Technical tec7 = new Technical(null, "Albert Einstein", "153.513.480-12", "einsteinAlber@mail.com", encoder.encode("123"));

		List<Technical> technicians = Arrays.asList(tec1, tec2, tec3, tec4, tec5, tec6, tec7);
		List<Technical> savedTechnicians = personRepository.saveAll(technicians);

		Customer cus1 = new Customer(null, "Nikola Tesla", "482.929.470-19", "tesla@mail.com", encoder.encode("123"));
		Customer cus2 = new Customer(null, "Albert Einstein", "912.997.500-01", "alberteinstein@mail.com", encoder.encode("123"));
		Customer cus3 = new Customer(null, "Ada Lovelace", "610.526.440-23", "lovelace@mail.com", encoder.encode("123"));
		Customer cus4 = new Customer(null, "Jane Austen", "401.814.150-54", "austen@mail.com", encoder.encode("123"));
		Customer cus5 = new Customer(null, "Carl Sagan", "286.008.160-76", "carlsagan@mail.com", encoder.encode("123"));
		Customer cus6 = new Customer(null, "Stephen Hawking", "466.894.050-01", "hawking@mail.com", encoder.encode("123"));

		List<Customer> customers = Arrays.asList(cus1, cus2, cus3, cus4, cus5, cus6);
		List<Customer> savedCustomers = personRepository.saveAll(customers);

		Ticket t1 = new Ticket(null, Priority.HIGH, Status.OPENED, "Ticket 1", "Support Request 1",
				savedTechnicians.get(1), savedCustomers.get(0));
		Ticket t2 = new Ticket(null, Priority.LOW, Status.CLOSED, "Ticket 2", "Support Request 2",
				savedTechnicians.get(2), savedCustomers.get(1));
		Ticket t3 = new Ticket(null, Priority.LOW, Status.CLOSED, "Ticket 3", "Support Request 3",
				savedTechnicians.get(3), savedCustomers.get(2));
		Ticket t4 = new Ticket(null, Priority.MEDIUM, Status.PROGRESS, "Ticket 4", "Support Request 4",
				savedTechnicians.get(4), savedCustomers.get(3));
		Ticket t5 = new Ticket(null, Priority.MEDIUM, Status.OPENED, "Ticket 5", "Support Request 5",
				savedTechnicians.get(5), savedCustomers.get(4));
		Ticket t6 = new Ticket(null, Priority.HIGH, Status.PROGRESS, "Ticket 6", "Support Request 6",
				savedTechnicians.get(6), savedCustomers.get(5));

		List<Ticket> tickets = Arrays.asList(t1, t2, t3, t4, t5, t6);
		ticketRepository.saveAll(tickets);
	}
}
