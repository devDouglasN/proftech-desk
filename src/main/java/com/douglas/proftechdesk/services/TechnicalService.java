package com.douglas.proftechdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglas.proftechdesk.domain.Person;
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
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com ID: " + id));
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
				throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
			}
		}

		if (!oldObj.getEmail().equals(objDTO.getEmail())) {
			boolean emailExists = personRepository.existsByEmail(objDTO.getEmail());
			if (emailExists) {
				throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
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
			throw new DataIntegrityViolationException("O técnico possui uma ordem de serviço e não pode ser excluído!");
		}
		technicalRepository.deleteById(id);
	}
	
	private void validationCpfAndEmail(TechnicalDTO objDTO) {
		Optional<Person> obj = personRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		obj = personRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}
	
}

