package com.douglas.proftechdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglas.proftechdesk.domain.Person;
import com.douglas.proftechdesk.domain.dtos.RegisterDTO;
import com.douglas.proftechdesk.repositories.PersonRepository;
import com.douglas.proftechdesk.security.UserSS;
import com.douglas.proftechdesk.services.exceptions.DataIntegrityViolationException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private final PersonRepository personRepository;

	public UserDetailsServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Person> user = personRepository.findByEmail(email);
		
		if (user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getPassword(),
					user.get().getProfiles());
		}
		
		throw new UsernameNotFoundException("Email user: " + email + " not found.");
	}
	
	public void register(RegisterDTO user) {
		
        if(this.personRepository.findByEmail(user.email()).isPresent()){
            throw new DataIntegrityViolationException("Já existe um usúario com este email! ");
        }
        if(this.personRepository.findByCpf(user.cpf()).isPresent()){
            throw new DataIntegrityViolationException("Já existe um usúario com este CPF!");
        }
        
        String encrypti = new BCryptPasswordEncoder().encode(user.password());
        Person person = new Person(user.name(), user.cpf(), user.email(), encrypti);
        personRepository.save(person);

    }
}
