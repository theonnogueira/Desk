package com.theo.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theo.helpdesk.domain.Called;
import com.theo.helpdesk.repositories.CalledRepository;
import com.theo.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class CalledService {

	@Autowired
	private CalledRepository calledRepository;

	public Called findById(Integer id) {
		Optional<Called> obj = calledRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Called> findAll() {
		return calledRepository.findAll();
	}

}
