package com.theo.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theo.helpdesk.domain.Called;
import com.theo.helpdesk.domain.Client;
import com.theo.helpdesk.domain.Technician;
import com.theo.helpdesk.domain.enums.Priority;
import com.theo.helpdesk.domain.enums.Profile;
import com.theo.helpdesk.domain.enums.Status;
import com.theo.helpdesk.repositories.CalledRepository;
import com.theo.helpdesk.repositories.ClientRepository;
import com.theo.helpdesk.repositories.TechnicianRepository;

@Service
public class DBService {

	@Autowired
	private TechnicianRepository technicianRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CalledRepository calledRespository;

	public void instanciaDB() {

		Technician tec1 = new Technician();
		Technician tec2 = new Technician(null, "Théo Nogueira", "101.060.474-00", "theonogueira@outlook.com", "321");
		Technician tec3 = new Technician(null, "Nelsinho", "550.639.070-08", "nelsinho@outlook.com", "111");
		tec2.addProfile(Profile.ADMIN);
		tec3.addProfile(Profile.TECHNICIAN);

		Client cli1 = new Client();
		Client cli2 = new Client(null, "Apólo", "373.844.790-37", "apolodog@gmail.com", "456");

		Called called1 = new Called();
		Called called2 = new Called(null, Priority.AVERAGE, Status.PROGRESS, "One called", "First comment", tec2, cli2);

		technicianRepository.saveAll(Arrays.asList(tec2));
		technicianRepository.saveAll(Arrays.asList(tec3));
		clientRepository.saveAll(Arrays.asList(cli2));
		calledRespository.saveAll(Arrays.asList(called2));
	}

}
