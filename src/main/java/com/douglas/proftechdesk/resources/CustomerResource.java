package com.douglas.proftechdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.proftechdesk.domain.Customer;
import com.douglas.proftechdesk.domain.dtos.CustomerDTO;
import com.douglas.proftechdesk.services.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
		Customer object = customerService.findById(id);
		return ResponseEntity.ok().body(new CustomerDTO(object));
	}

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<Customer> list = customerService.findAll();
		List<CustomerDTO> listDTO = list.stream().map(CustomerDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO objectDTO) {
		Customer newObj = customerService.create(objectDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @Valid @RequestBody CustomerDTO objDTO) {
		Customer obj = customerService.update(id, objDTO);
		return ResponseEntity.ok().body(new CustomerDTO(obj));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
		customerService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Technician with ID " + id + " successfully deleted!");
	}
}
