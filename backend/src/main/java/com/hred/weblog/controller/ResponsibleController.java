package com.hred.weblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hred.weblog.DTO.ResponsibleDTO;
import com.hred.weblog.model.Responsible;
import com.hred.weblog.service.ResponsibleService;

@RestController
@RequestMapping(value = "/responsible")
public class ResponsibleController {
	@Autowired
	private ResponsibleService service;

	@GetMapping
	public Page<Responsible> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping(value = "/{id}")
	public Responsible findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Responsible save(@RequestBody ResponsibleDTO responsibleDTO) {
		return service.save(responsibleDTO);
	}

}
