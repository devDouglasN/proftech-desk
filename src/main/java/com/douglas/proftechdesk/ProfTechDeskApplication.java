package com.douglas.proftechdesk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.douglas.proftechdesk.domain.Customer;
import com.douglas.proftechdesk.domain.Technical;
import com.douglas.proftechdesk.domain.Ticket;
import com.douglas.proftechdesk.domain.enums.Priority;
import com.douglas.proftechdesk.domain.enums.Profile;
import com.douglas.proftechdesk.domain.enums.Status;
import com.douglas.proftechdesk.repositories.CustomerRepository;
import com.douglas.proftechdesk.repositories.TechnicalRepository;
import com.douglas.proftechdesk.repositories.TicketRepository;

@SpringBootApplication
public class ProfTechDeskApplication implements CommandLineRunner {

	@Autowired
	private TechnicalRepository technicalRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TicketRepository ticketRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProfTechDeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Technical tec1 = new Technical(null, "Douglas do Nascimento", "45217854623", "douglas@mail.com", "123");
		tec1.addProfiles(Profile.ADMIN);

		Customer cus1 = new Customer(null, "Edwin Hubble", "25478546399", "hubble@mail.com", "123");

		Ticket t1 = new Ticket(null, Priority.HIGH, Status.PROGRESS, "Ticket  01", "First ticket", tec1, cus1);

		technicalRepository.saveAll(List.of(tec1));
		customerRepository.saveAll(List.of(cus1));
		ticketRepository.saveAll(List.of(t1));
	}
}
