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

import com.hred.weblog.DTO.DeliveryDTO;
import com.hred.weblog.model.Delivery;
import com.hred.weblog.service.DeliveryService;

@RestController
@RequestMapping(value = "/delivery")
public class DeliveryController {
	@Autowired
	private DeliveryService service;

	@GetMapping
	public Page<Delivery> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping(value = "/{id}")
	public Delivery findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public Delivery save(@RequestBody DeliveryDTO deliveryDTO) {
		return service.save(deliveryDTO);
	}

}
