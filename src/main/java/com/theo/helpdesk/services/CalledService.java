package com.theo.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theo.helpdesk.domain.Called;
import com.theo.helpdesk.domain.Client;
import com.theo.helpdesk.domain.Technician;
import com.theo.helpdesk.domain.dtos.CalledDTO;
import com.theo.helpdesk.domain.enums.Priority;
import com.theo.helpdesk.domain.enums.Status;
import com.theo.helpdesk.repositories.CalledRepository;
import com.theo.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class CalledService {

	@Autowired
	private CalledRepository calledRepository;

	@Autowired
	private TechnicianService technicianService;

	@Autowired
	private ClientService clientService;

	public Called findById(Integer id) {
		Optional<Called> obj = calledRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Called> findAll() {
		return calledRepository.findAll();
	}

	public Called create(@Valid CalledDTO objDTO) {
		return calledRepository.save(newCalled(objDTO));
	}

	public Called update(Integer id, @Valid CalledDTO objDTO) {
		objDTO.setId(id);
		Called oldObj = findById(id);
		oldObj = newCalled(objDTO);
		return calledRepository.save(oldObj);
	}

	private Called newCalled(CalledDTO obj) {
		Technician technician = technicianService.findById(obj.getTechnician());
		Client client = clientService.findById(obj.getClient());

		Called called = new Called();
		if (obj.getId() != null) {
			called.setId(obj.getId());
		}

		if (obj.getStatus().equals(2)) {
			called.setClosureDate(LocalDate.now());
		}

		called.setTechnician(technician);
		called.setClient(client);
		called.setPriority(Priority.toEnum(obj.getPriority()));
		called.setStatus(Status.toEnum(obj.getStatus()));
		called.setTitle(obj.getTitle());
		called.setComments(obj.getComments());

		return called;

	}

}
