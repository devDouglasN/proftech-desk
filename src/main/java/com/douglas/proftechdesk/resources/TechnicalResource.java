package com.douglas.proftechdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.proftechdesk.domain.Technical;
import com.douglas.proftechdesk.domain.dtos.TechnicalDTO;
import com.douglas.proftechdesk.services.TechnicalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/technicals")
public class TechnicalResource {

	@Autowired
	private TechnicalService technicalService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TechnicalDTO> findById(@PathVariable Integer id) {
		Technical object = technicalService.findById(id);
		return ResponseEntity.ok().body(new TechnicalDTO(object));
	}

	@GetMapping
	public ResponseEntity<List<TechnicalDTO>> findAll() {
		List<Technical> list = technicalService.findAll();
		List<TechnicalDTO> listDTO = list.stream().map(TechnicalDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TechnicalDTO> create(@Valid @RequestBody TechnicalDTO objectDTO) {
		Technical newObj = technicalService.create(objectDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<TechnicalDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicalDTO objDTO) {
		Technical obj = technicalService.update(id, objDTO);
		return ResponseEntity.ok().body(new TechnicalDTO(obj));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
		technicalService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Technician with ID " + id + " successfully deleted!");
	}
}
