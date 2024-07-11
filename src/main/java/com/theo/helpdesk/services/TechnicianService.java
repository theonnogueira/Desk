package com.theo.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theo.helpdesk.domain.People;
import com.theo.helpdesk.domain.Technician;
import com.theo.helpdesk.domain.dtos.TechnicianDTO;
import com.theo.helpdesk.repositories.PeopleRepository;
import com.theo.helpdesk.repositories.TechnicianRepository;
import com.theo.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.theo.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TechnicianService {

	@Autowired
	private TechnicianRepository technicianRepository;

	@Autowired
	private PeopleRepository peopleRepository;

	public Technician findById(Integer id) {
		Optional<Technician> obj = technicianRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Technician> findAll() {
		return technicianRepository.findAll();
	}

	public Technician create(TechnicianDTO objDTO) {
		objDTO.setId(null);
		validateByCpfAndEmail(objDTO);
		Technician newObj = new Technician(objDTO);
		return technicianRepository.save(newObj);
	}

	public Technician update(Integer id, @Valid TechnicianDTO objDTO) {
		objDTO.setId(id);
		Technician oldObj = findById(id);
		validateByCpfAndEmail(objDTO);
		oldObj = new Technician(objDTO);
		return technicianRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Technician obj = findById(id);
		if (obj.getCalleds().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		}
		technicianRepository.deleteById(id);
	}

	private void validateByCpfAndEmail(TechnicianDTO objDTO) {
		Optional<People> obj = peopleRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		obj = peopleRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-MAIL já cadastrado no sistema!");
		}
	}

}
