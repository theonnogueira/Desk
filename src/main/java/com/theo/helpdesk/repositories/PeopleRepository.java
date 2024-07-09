package com.theo.helpdesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theo.helpdesk.domain.People;

public interface PeopleRepository extends JpaRepository<People, Integer> {
	Optional<People> findByCpf(String cpf);

	Optional<People> findByEmail(String email);
}
