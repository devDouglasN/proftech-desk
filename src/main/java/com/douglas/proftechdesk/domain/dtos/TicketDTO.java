package com.douglas.proftechdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.douglas.proftechdesk.domain.Ticket;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TicketDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate openingDate = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate closingDate = LocalDate.now();
	private Integer priority;
	private Integer status;
	private String title;
	private String observations;
	private Integer technical;
	private Integer customer;
	private String technicianName;
	private String customerName;

	public TicketDTO() {
	}

	public TicketDTO(Ticket ticket) {
		super();
		this.id = ticket.getId();
		this.openingDate = ticket.getOpeningDate();
		this.closingDate = ticket.getClosingDate();
		this.priority = ticket.getPriority().getCode();
		this.status = ticket.getStatus().getCode();
		this.title = ticket.getTitle();
		this.observations = ticket.getObservations();
		this.technical = ticket.getTechnical().getId();
		this.customer = ticket.getCustomer().getId();
		this.technicianName = ticket.getTechnical().getName();
		this.customerName = ticket.getCustomer().getName();
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

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	public Integer getTechnical() {
		return technical;
	}

	public void setTechnical(Integer technical) {
		this.technical = technical;
	}

	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

	public String getTechnicianName() {
		return technicianName;
	}

	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
