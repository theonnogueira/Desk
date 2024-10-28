package com.theo.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.theo.helpdesk.domain.Client;
import com.theo.helpdesk.domain.People;
import com.theo.helpdesk.domain.dtos.ClientDTO;
import com.theo.helpdesk.repositories.ClientRepository;
import com.theo.helpdesk.repositories.PeopleRepository;
import com.theo.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.theo.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private PeopleRepository peopleRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public Client findById(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));

	}

	public List<Client> finAll() {
		return clientRepository.findAll();
	}

	public Client create(ClientDTO objDTO) {
		objDTO.setId(null);
		objDTO.setPassword(encoder.encode(objDTO.getPassword()));
		validateByCpfAndEmail(objDTO);
		Client newObj = new Client(objDTO);
		return clientRepository.save(newObj);
	}

	private void validateByCpfAndEmail(ClientDTO objDTO) {
		Optional<People> obj = peopleRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		obj = peopleRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-MAIL já cadastrado no sistema!");
		}

	}

	public Client update(Integer id, @Valid ClientDTO objDTO) {
		objDTO.setId(id);
		Client oldObj = findById(id);
		validateByCpfAndEmail(objDTO);
		oldObj = new Client(objDTO);
		return clientRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Client obj = findById(id);
		if (obj.getCalleds().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
		}
		clientRepository.deleteById(id);

	}
}
