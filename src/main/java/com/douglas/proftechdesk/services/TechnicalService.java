package com.douglas.proftechdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglas.proftechdesk.domain.Technical;
import com.douglas.proftechdesk.domain.dtos.TechnicalDTO;
import com.douglas.proftechdesk.repositories.PersonRepository;
import com.douglas.proftechdesk.repositories.TechnicalRepository;
import com.douglas.proftechdesk.services.exceptions.DataIntegrityViolationException;
import com.douglas.proftechdesk.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TechnicalService {

	@Autowired
	private TechnicalRepository technicalRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public Technical findById(Integer id) {
		Optional<Technical> obj = technicalRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with ID: " + id));
	}

	public List<Technical> findAll() {
		return technicalRepository.findAll();
	}

	public Technical create(TechnicalDTO objectDTO) {
		objectDTO.setId(null);
		objectDTO.setPassword(encoder.encode(objectDTO.getPassword()));
		validationCpfAndEmail(objectDTO);
		Technical newObj = new Technical(objectDTO);
		return technicalRepository.save(newObj);
	}

	public Technical update(Integer id, @Valid TechnicalDTO objDTO) {
		Technical oldObj = findById(id);

		if (!oldObj.getCpf().equals(objDTO.getCpf())) {
			boolean cpfExists = personRepository.existsByCpf(objDTO.getCpf());
			if (cpfExists) {
				throw new DataIntegrityViolationException("CPF already registered on system!");
			}
		}

		if (!oldObj.getEmail().equals(objDTO.getEmail())) {
			boolean emailExists = personRepository.existsByEmail(objDTO.getEmail());
			if (emailExists) {
				throw new DataIntegrityViolationException("E-mail already registered on system!");
			}
		}

		if (!objDTO.getPassword().equals(oldObj.getPassword())) {
			oldObj.setPassword(encoder.encode(objDTO.getPassword()));
		}

		oldObj.setName(objDTO.getName());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setEmail(objDTO.getEmail());

		return technicalRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Technical obj = findById(id);
		if (obj.getTickets().size() > 0) {
			throw new DataIntegrityViolationException("Technician has a service order and cannot be deleted!");
		}
		technicalRepository.deleteById(id);
	}

	private void validationCpfAndEmail(TechnicalDTO objDTO) {
		boolean cpf = personRepository.existsByCpf(objDTO.getCpf());
		if (cpf) {
			throw new DataIntegrityViolationException("CPF already registered in the system!");
		}

		boolean email = personRepository.existsByEmail(objDTO.getEmail());
		if (email) {
			throw new DataIntegrityViolationException("E-Mail already register on system!");
		}
	}
}
