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

import com.hred.weblog.DTO.PackDTO;
import com.hred.weblog.model.Pack;
import com.hred.weblog.service.PackService;

@RestController
@RequestMapping(value = "/pack")
public class PackController {
	@Autowired
	private PackService service;

	@GetMapping
	public Page<Pack> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping(value = "/{id}")
	public Pack findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Pack save(@RequestBody PackDTO packDTO) {
		return service.save(packDTO);
	}

}
