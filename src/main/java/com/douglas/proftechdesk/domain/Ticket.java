package com.douglas.proftechdesk.domain;

import java.time.LocalDate;
import java.util.Objects;

import com.douglas.proftechdesk.domain.enums.Priority;
import com.douglas.proftechdesk.domain.enums.Status;

public class Ticket {

	private Integer id;
	private LocalDate openingDate = LocalDate.now();
	private LocalDate closingDate = LocalDate.now();
	private Priority priority;
	private Status status;
	private String title;
	private String observations;

	private Technical technical;
	private Customer customer;

	public Ticket() {
		super();
	}

	public Ticket(Integer id, Priority priority, Status status, String title, String observations, Technical technical,
			Customer customer) {
		this.id = id;
		this.priority = priority;
		this.status = status;
		this.title = title;
		this.observations = observations;
		this.technical = technical;
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Technical getTechnical() {
		return technical;
	}

	public void setTechnical(Technical technical) {
		this.technical = technical;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Ticket setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ticket ticket = (Ticket) o;
		return id == ticket.id;
	}
}
