package com.theo.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instanciaDB() {

		Technician tec1 = new Technician();
		Technician tec2 = new Technician(null, "Théo Nogueira", "101.060.474-00", "theonogueira@outlook.com",
				encoder.encode("321"));
		Technician tec3 = new Technician(null, "Nelsinho", "550.639.070-08", "nelsinho@outlook.com",
				encoder.encode("111"));
		Technician tec4 = new Technician(null, "Suzy", "815.745.220-19", "suzy@yahoo.com.br",
				encoder.encode("69851544421"));
		Technician tec5 = new Technician(null, "Lula", "555.892.480-27", "lula@gmail.com",
				encoder.encode("62204132054"));
		Technician tec6 = new Technician(null, "Jack Daniels", "651.978.150-94", "jack@hotmail.com.br",
				encoder.encode("369845"));
		tec2.addProfile(Profile.ADMIN);
		tec3.addProfile(Profile.TECHNICIAN);

		Client cli1 = new Client();
		Client cli2 = new Client(null, "Apólo", "373.844.790-37", "apolodog@gmail.com", encoder.encode("456"));
		Client cli3 = new Client(null, "Dilma", "994.353.720-58", "dilma@gmail.com", encoder.encode("456"));
		Client cli4 = new Client(null, "Draco", "501.283.950-10", "draco@gmail.com", encoder.encode("456"));
		Client cli5 = new Client(null, "Dobby", "245.416.960-61", "doby@gmail.com", encoder.encode("456"));

		Called called1 = new Called();
		Called called2 = new Called(null, Priority.AVERAGE, Status.PROGRESS, "One called", "First comment", tec2, cli2);

		technicianRepository.saveAll(Arrays.asList(tec2));
		technicianRepository.saveAll(Arrays.asList(tec3));
		clientRepository.saveAll(Arrays.asList(cli2));
		calledRespository.saveAll(Arrays.asList(called2));
		technicianRepository.saveAll(Arrays.asList(tec4));
		clientRepository.saveAll(Arrays.asList(cli3));
		technicianRepository.saveAll(Arrays.asList(tec5));
		clientRepository.saveAll(Arrays.asList(cli4));
		technicianRepository.saveAll(Arrays.asList(tec6));
		clientRepository.saveAll(Arrays.asList(cli5));
	}

}
