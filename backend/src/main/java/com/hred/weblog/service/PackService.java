package com.hred.weblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hred.weblog.DTO.PackDTO;
import com.hred.weblog.model.Pack;
import com.hred.weblog.repository.PackRepository;

@Service
public class PackService {

	@Autowired
	private PackRepository repository;

	@Transactional(readOnly = true)
	public Page<Pack> findAll(Pageable pageable) {
		Page<Pack> pack = repository.findAll(pageable);
		return pack;
	}

	@Transactional(readOnly = true)
	public Pack findById(Long id) {
		Pack pack = repository.findById(id).get();
		return pack;

	}

	@Transactional
	public Pack save(PackDTO packDto) {
		Pack pack = new Pack();
		pack.setHeight(packDto.getHeight());
		pack.setWidth(packDto.getWidth());
		pack.setLength(packDto.getLength());

		float cubicMeter = (pack.getHeight() / 100) * (pack.getWidth() / 100) * (pack.getLength() / 100);

		pack.setCubicMeter(cubicMeter);

		return repository.save(pack);

	}

}
