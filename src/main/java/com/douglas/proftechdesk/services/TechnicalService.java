package com.douglas.proftechdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.proftechdesk.domain.Technical;
import com.douglas.proftechdesk.repositories.TechnicalRepository;

@Service
public class TechnicalService {

	@Autowired
	private TechnicalRepository technicalRepository;

	public Technical findById(Integer id) {
		Optional<Technical> obj = technicalRepository.findById(id);
		return obj.orElse(null);
	}
}
