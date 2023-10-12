package com.douglas.proftechdesk.domain.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.douglas.proftechdesk.domain.Technical;
import com.douglas.proftechdesk.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class TechnicalDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotNull(message = "Field NAME is required")
	protected String name;
	@NotNull(message = "Field CPF is required")
	protected String cpf;
	@NotNull(message = "Field EMAIL is required")
	protected String email;
	@NotNull(message = "Field PASSWORD is required")
	protected String password;
	protected Set<Integer> profiles = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();

	public TechnicalDTO() {
		super();
		addProfile(Profile.CUSTOMER);
	}

	public TechnicalDTO(Technical obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profiles = obj.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
		this.creationDate = obj.getCreatedAt();
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

	public TechnicalDTO setName(String name) {
		this.name = name;
		return this;
	}

	public String getCpf() {
		return cpf;
	}

	public TechnicalDTO setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public TechnicalDTO setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public TechnicalDTO setPassword(String password) {
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

	public TechnicalDTO setCreatedAt(LocalDate createdAt) {
		this.creationDate = createdAt;
		return this;
	}
}
