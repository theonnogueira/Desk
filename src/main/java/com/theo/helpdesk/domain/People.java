package com.theo.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.theo.helpdesk.domain.enums.Profile;

@Entity(name = "TB_PEOPLE")
public abstract class People implements Serializable {
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
	protected LocalDate CreatedData = LocalDate.now();

	public People() {
		super();
		addProfile(Profile.CLIENT);
	}

	public People(Integer id, String name, String cpf, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		addProfile(Profile.CLIENT);
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

	public void addProfile(Profile profile) {
		this.profiles.add(profile.getCode());
	}

	public LocalDate getCreatedData() {
		return CreatedData;
	}

	public void setCreatedData(LocalDate createdData) {
		CreatedData = createdData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		People other = (People) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

}
