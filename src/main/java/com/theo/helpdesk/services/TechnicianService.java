package com.theo.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theo.helpdesk.domain.Technician;
import com.theo.helpdesk.repositories.TechnicianRepository;

@Service
public class TechnicianService {

	@Autowired
	private TechnicianRepository technicianRepository;

	public Technician findById(Integer id) {
		Optional<Technician> obj = technicianRepository.findById(id);
		return obj.orElse(null);
	}
}
