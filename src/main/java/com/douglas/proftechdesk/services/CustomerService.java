package com.douglas.proftechdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.proftechdesk.domain.Person;
import com.douglas.proftechdesk.domain.dtos.CustomerDTO;
import com.douglas.proftechdesk.domain.Customer;
import com.douglas.proftechdesk.repositories.PersonRepository;
import com.douglas.proftechdesk.repositories.CustomerRepository;
import com.douglas.proftechdesk.services.exceptions.DataIntegrityViolationException;
import com.douglas.proftechdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PersonRepository personRepository;

	public Customer findById(Integer id) {
		Optional<Customer> obj = customerRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with ID: " + id));
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer create(CustomerDTO objectDTO) {
		objectDTO.setId(null);
		validationCpfAndEmail(objectDTO);
		Customer newObj = new Customer(objectDTO);
		return customerRepository.save(newObj);
	}

	public Customer update(Integer id, @Valid CustomerDTO objDTO) {
		objDTO.setId(id);
		Customer oldObj = findById(id);
		validationCpfAndEmail(objDTO);
		oldObj = new Customer(objDTO);
		return customerRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Customer obj = findById(id);
		if (obj.getTickets().size() > 0) {
			throw new DataIntegrityViolationException("Customer has a service order and cannot be deleted!");
		}
		customerRepository.deleteById(id);
	}

	private void validationCpfAndEmail(CustomerDTO objDTO) {
		Optional<Person> obj = personRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF already registered in the system!");
		}

		obj = personRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email a	lready registered in the system!");
		}
	}
}
