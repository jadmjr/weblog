package com.hred.weblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hred.weblog.DTO.ResponsibleDTO;
import com.hred.weblog.model.Responsible;
import com.hred.weblog.model.ResponsibleAddress;
import com.hred.weblog.repository.ResponsibleRepository;

@Service
public class ResponsibleService {

	@Autowired
	private ResponsibleRepository repository;

	@Transactional(readOnly = true)
	public Page<Responsible> findAll(Pageable pageable) {
		Page<Responsible> responsible = repository.findAll(pageable);
		return responsible;
	}

	@Transactional(readOnly = true)
	public Responsible findById(Long id) {
		Responsible responsible = repository.findById(id).get();
		return responsible;

	}

	@Transactional
	public Responsible save(ResponsibleDTO responsibleDto) {

		ResponsibleAddress responsibleAdress = new ResponsibleAddress();
		
		responsibleAdress.setStreetName(responsibleDto.getResponsibleAdress().getStreetName());
		responsibleAdress.setNumber(responsibleDto.getResponsibleAdress().getNumber());
		responsibleAdress.setComplement(responsibleDto.getResponsibleAdress().getComplement());
		responsibleAdress.setCity(responsibleDto.getResponsibleAdress().getCity());
		responsibleAdress.setEstate(responsibleDto.getResponsibleAdress().getEstate());
		responsibleAdress.setZipCode(responsibleDto.getResponsibleAdress().getZipCode());
		responsibleAdress.setCountry(responsibleDto.getResponsibleAdress().getCountry());

		Responsible responsible = new Responsible();

		responsible.setName(responsibleDto.getName());
		responsible.setEmail(responsibleDto.getEmail());
		responsible.setPhone(responsibleDto.getPhone());
		responsible.setDocumentNumber(responsibleDto.getDocumentNumber());
		responsible.setResponsibleAdress(responsibleAdress);		

		return repository.save(responsible);

	}

}
