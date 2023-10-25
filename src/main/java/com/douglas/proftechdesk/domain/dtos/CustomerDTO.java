package com.douglas.proftechdesk.domain.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.br.CPF;

import com.douglas.proftechdesk.domain.Customer;
import com.douglas.proftechdesk.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class CustomerDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotNull(message = "Field NAME is required")
	protected String name;
	@NotNull(message = "Field CPF is required")
	@CPF
	protected String cpf;
	@NotNull(message = "Field EMAIL is required")
	protected String email;
	@NotNull(message = "Field PASSWORD is required")
	protected String password;
	
	protected Set<Integer> profiles = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();

	public CustomerDTO() {
		super();
		addProfile(Profile.CUSTOMER);
	}

	public CustomerDTO(Customer obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profiles = obj.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
		this.creationDate = obj.getCreatedAt();
		addProfile(Profile.CUSTOMER);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public CustomerDTO setName(String name) {
		this.name = name;
		return this;
	}

	public String getCpf() {
		return cpf;
	}

	public CustomerDTO setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public CustomerDTO setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public CustomerDTO setPassword(String password) {
		this.password = password;
		return this;
	}

	public Set<Profile> getProfiles() {
		return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
	}

	public void addProfile(Profile profile) {
		this.profiles.add(profile.getCode());
	}

	public LocalDate getCreatedAt() {
		return creationDate;
	}

	public CustomerDTO setCreatedAt(LocalDate createdAt) {
		this.creationDate = createdAt;
		return this;
	}
}
