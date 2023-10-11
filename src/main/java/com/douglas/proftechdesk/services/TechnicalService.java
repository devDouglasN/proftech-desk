package com.douglas.proftechdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.proftechdesk.domain.Person;
import com.douglas.proftechdesk.domain.Technical;
import com.douglas.proftechdesk.domain.dtos.TechnicalDTO;
import com.douglas.proftechdesk.repositories.PersonRepository;
import com.douglas.proftechdesk.repositories.TechnicalRepository;
import com.douglas.proftechdesk.services.exceptions.DataIntegrityViolationException;
import com.douglas.proftechdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TechnicalService {

	@Autowired
	private TechnicalRepository technicalRepository;

	@Autowired
	private PersonRepository personRepository;

	public Technical findById(Integer id) {
		Optional<Technical> obj = technicalRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with ID: " + id));
	}

	public List<Technical> findAll() {
		return technicalRepository.findAll();
	}

	public Technical create(TechnicalDTO objectDTO) {
		objectDTO.setId(null);
		validationCpfAndEmail(objectDTO);
		Technical newObj = new Technical(objectDTO);
		return technicalRepository.save(newObj);
	}

	private void validationCpfAndEmail(TechnicalDTO objDTO) {
		Optional<Person> obj = personRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF already registered in the system!");
		}

		obj = personRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email already registered in the system!");
		}
	}
}
