package com.douglas.proftechdesk.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.douglas.proftechdesk.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String name;

	@Column(unique = true)
	protected String cpf;

	@Column(unique = true)
	protected String email;
	protected String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PROFILES")
	protected Set<Integer> profiles = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();

	public Person() {
		super();
		addProfiles(Profile.CUSTOMER);
	}

	public Person(Integer id, String name, String cpf, String email, String password) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		addProfiles(Profile.CUSTOMER);
	}

	public Integer getId() {
		return id;
	}

	public Person setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Person setName(String name) {
		this.name = name;
		return this;
	}

	public String getCpf() {
		return cpf;
	}

	public Person setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Person setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Person setPassword(String password) {
		this.password = password;
		return this;
	}

	public Set<Profile> getProfiles() {
		return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
	}

	public void addProfiles(Profile profile) {
		this.profiles.add(profile.getCode());
	}

	public LocalDate getCreatedAt() {
		return creationDate;
	}

	public Person setCreatedAt(LocalDate createdAt) {
		this.creationDate = createdAt;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Person person = (Person) o;
		return id == person.id && Objects.equals(cpf, person.cpf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cpf);
	}
}
