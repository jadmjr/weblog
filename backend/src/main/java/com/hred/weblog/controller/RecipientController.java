package com.hred.weblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hred.weblog.DTO.RecipientDTO;
import com.hred.weblog.model.Recipient;
import com.hred.weblog.service.RecipientService;

@RestController
@CrossOrigin
@RequestMapping(value = "/recipient")
public class RecipientController {
	@Autowired
	private RecipientService service;

	@GetMapping
	public Page<Recipient> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping(value = "/{id}")
	public Recipient findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Recipient save(@RequestBody RecipientDTO recipientDTO) {
		return service.save(recipientDTO);
	}

}
