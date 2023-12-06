package com.douglas.proftechdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.douglas.proftechdesk.domain.Person;
import com.douglas.proftechdesk.repositories.PersonRepository;
import com.douglas.proftechdesk.security.UserSS;

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
}
