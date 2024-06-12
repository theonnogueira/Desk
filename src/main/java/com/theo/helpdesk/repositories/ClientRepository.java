package com.theo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theo.helpdesk.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
