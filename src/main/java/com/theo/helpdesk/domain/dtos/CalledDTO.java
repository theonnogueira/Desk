package com.theo.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.theo.helpdesk.domain.Called;

public class CalledDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate OpeningDate = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate ClosureDate;
	@NotNull(message = "The PRIORITY field is required")
	private Integer priority;
	@NotNull(message = "The STATUS field is required")
	private Integer status;
	@NotNull(message = "The TITLE field is required")
	private String title;
	@NotNull(message = "The COMMENTS field is required")
	private String comments;
	@NotNull(message = "The TECHNICIAN field is required")
	private Integer technician;
	@NotNull(message = "The CLIENT field is required")
	private Integer client;
	private String nameTechnician;
	private String nameClient;

	public CalledDTO() {
		super();
	}

	public CalledDTO(Called obj) {
		super();
		this.id = obj.getId();
		OpeningDate = obj.getOpeningDate();
		ClosureDate = obj.getClosureDate();
		this.priority = obj.getPriority().getCode();
		this.status = obj.getStatus().getCode();
		this.title = obj.getTitle();
		this.comments = obj.getComments();
		this.technician = obj.getTechnician().getId();
		this.client = obj.getClient().getId();
		this.nameTechnician = obj.getTechnician().getName();
		this.nameClient = obj.getClient().getName();
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getTechnician() {
		return technician;
	}

	public void setTechnician(Integer technician) {
		this.technician = technician;
	}

	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	public String getNameTechnician() {
		return nameTechnician;
	}

	public void setNameTechnician(String nameTechnician) {
		this.nameTechnician = nameTechnician;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

}
