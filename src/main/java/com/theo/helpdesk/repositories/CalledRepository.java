package com.theo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theo.helpdesk.domain.Called;

public interface CalledRepository extends JpaRepository<Called, Integer> {

}
