package com.theo.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.theo.helpdesk.domain.Client;
import com.theo.helpdesk.domain.enums.Profile;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Integer id;

	@NotNull(message = "Campo obrigatório.")
	protected String name;

	@NotNull(message = "Campo obrigatório.")
	protected String cpf;

	@NotNull(message = "Campo obrigatório.")
	protected String email;

	@NotNull(message = "Campo obrigatório.")
	protected String password;

	protected Set<Integer> profiles = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate CreatedData = LocalDate.now();

	public ClientDTO() {
		super();
		addProfiles(Profile.CLIENT);
	}

	public ClientDTO(Client obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profiles = obj.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
		CreatedData = obj.getCreatedData();
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

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}

	public void addProfiles(Profile profile) {
		this.profiles.add(profile.getCode());
	}

	public LocalDate getCreatedData() {
		return CreatedData;
	}

	public void setCreatedData(LocalDate createdData) {
		CreatedData = createdData;
	}

}
