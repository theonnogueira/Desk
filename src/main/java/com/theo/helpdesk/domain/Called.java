package com.theo.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.theo.helpdesk.domain.enums.Priority;
import com.theo.helpdesk.domain.enums.Status;

@Entity(name = "TB_CALLED")
public class Called implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate OpeningDate = LocalDate.now();

	@JsonFormat(pattern = "dd/MM/yyyy")

	private LocalDate ClosureDate;
	private Priority priority;
	private Status status;
	private String title;
	private String comments;

	@ManyToOne
	@JoinColumn(name = "technician_id")
	private Technician technician;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	public Called() {
		super();
	}

	public Called(Integer id, Priority priority, Status status, String title, String comments, Technician technician,
			Client client) {
		super();
		this.id = id;
		this.priority = priority;
		this.status = status;
		this.title = title;
		this.comments = comments;
		this.technician = technician;
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getOpeningDate() {
		return OpeningDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		OpeningDate = openingDate;
	}

	public LocalDate getClosureDate() {
		return ClosureDate;
	}

	public void setClosureDate(LocalDate closureDate) {
		ClosureDate = closureDate;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Called other = (Called) obj;
		return Objects.equals(id, other.id);
	}

}
