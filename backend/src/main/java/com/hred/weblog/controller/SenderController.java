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

import com.hred.weblog.DTO.SenderDTO;
import com.hred.weblog.model.Sender;
import com.hred.weblog.service.SenderService;

@RestController
@RequestMapping(value = "/sender")
public class SenderController {
	@Autowired
	private SenderService service;

	@GetMapping
	public Page<Sender> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping(value = "/{id}")
	public Sender findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Sender save(@RequestBody SenderDTO senderDTO) {
		return service.save(senderDTO);
	}

}
