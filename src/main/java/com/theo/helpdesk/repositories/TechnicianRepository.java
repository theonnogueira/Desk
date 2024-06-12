package com.theo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theo.helpdesk.domain.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {

}
