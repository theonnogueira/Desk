package com.theo.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theo.helpdesk.domain.Technician;
import com.theo.helpdesk.domain.dtos.TechnicianDTO;
import com.theo.helpdesk.services.TechnicianService;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianResource {

	@Autowired
	private TechnicianService technicianService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id) {
		Technician obj = this.technicianService.findById(id);
		return ResponseEntity.ok().body(new TechnicianDTO(obj));

	}

	@GetMapping
	public ResponseEntity<List<TechnicianDTO>> findAll() {
		List<Technician> list = technicianService.findAll();
		List<TechnicianDTO> listDTO = list.stream().map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

}
