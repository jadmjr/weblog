package com.hred.weblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hred.weblog.DTO.RecipientDTO;
import com.hred.weblog.model.Recipient;
import com.hred.weblog.model.RecipientAddress;
import com.hred.weblog.repository.RecipientRepository;

@Service
public class RecipientService {

	@Autowired
	private RecipientRepository repository;

	@Transactional(readOnly = true)
	public Page<Recipient> findAll(Pageable pageable) {
		Page<Recipient> recipient = repository.findAll(pageable);
		return recipient;
	}

	@Transactional(readOnly = true)
	public Recipient findById(Long id) {
		Recipient recipient = repository.findById(id).get();
		return recipient;

	}

	@Transactional
	public Recipient save(RecipientDTO recipientDto) {

		RecipientAddress recipientAdress = new RecipientAddress();
		
		recipientAdress.setStreetName(recipientDto.getRecipientAdress().getStreetName());
		recipientAdress.setNumber(recipientDto.getRecipientAdress().getNumber());
		recipientAdress.setComplement(recipientDto.getRecipientAdress().getComplement());
		recipientAdress.setNeighborhood(recipientDto.getRecipientAdress().getNeighborhood());
		recipientAdress.setCity(recipientDto.getRecipientAdress().getCity());
		recipientAdress.setState(recipientDto.getRecipientAdress().getState());
		recipientAdress.setZipCode(recipientDto.getRecipientAdress().getZipCode());
		recipientAdress.setCountry(recipientDto.getRecipientAdress().getCountry());

		Recipient recipient = new Recipient();

		recipient.setName(recipientDto.getName());
		recipient.setEmail(recipientDto.getEmail());
		recipient.setPhone(recipientDto.getPhone());
		recipient.setDocumentNumber(recipientDto.getDocumentNumber());
		recipient.setRecipientAdress(recipientAdress);		

		return repository.save(recipient);

	}

}
