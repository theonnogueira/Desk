package com.theo.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theo.helpdesk.domain.Technician;
import com.theo.helpdesk.domain.dtos.TechnicianDTO;
import com.theo.helpdesk.repositories.TechnicianRepository;
import com.theo.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TechnicianService {

	@Autowired
	private TechnicianRepository technicianRepository;

	public Technician findById(Integer id) {
		Optional<Technician> obj = technicianRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Technician> findAll() {
		return technicianRepository.findAll();
	}

	public Technician create(TechnicianDTO objDTO) {
		objDTO.setId(null);
		Technician newObj = new Technician(objDTO);
		return technicianRepository.save(newObj);
	}
}
