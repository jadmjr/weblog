package com.hred.weblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hred.weblog.DTO.SenderDTO;
import com.hred.weblog.model.Sender;
import com.hred.weblog.model.SenderAddress;
import com.hred.weblog.repository.SenderRepository;

@Service
public class SenderService {

	@Autowired
	private SenderRepository repository;

	@Transactional(readOnly = true)
	public Page<Sender> findAll(Pageable pageable) {
		Page<Sender> sender = repository.findAll(pageable);
		return sender;
	}

	@Transactional(readOnly = true)
	public Sender findById(Long id) {
		Sender sender = repository.findById(id).get();
		return sender;

	}

	@Transactional
	public Sender save(SenderDTO senderDto) {

		SenderAddress senderAdress = new SenderAddress();
		
		senderAdress.setStreetName(senderDto.getSenderAdress().getStreetName());
		senderAdress.setNumber(senderDto.getSenderAdress().getNumber());
		senderAdress.setComplement(senderDto.getSenderAdress().getComplement());
		senderAdress.setCity(senderDto.getSenderAdress().getCity());
		senderAdress.setEstate(senderDto.getSenderAdress().getEstate());
		senderAdress.setZipCode(senderDto.getSenderAdress().getZipCode());
		senderAdress.setCountry(senderDto.getSenderAdress().getCountry());

		Sender sender = new Sender();

		sender.setName(senderDto.getName());
		sender.setEmail(senderDto.getEmail());
		sender.setPhone(senderDto.getPhone());
		sender.setDocumentNumber(senderDto.getDocumentNumber());

		sender.setSenderAdress(senderAdress);
		//senderAdress.setSender(sender);

		//Sender ns = new Sender();
		//ns.set(sender);

		// senderAdress.setSender(sender);

		// sender.setSenderAdress(senderAdress);
		// sender.getSenderAdress().setSender(sender);

		/*
		 * SenderAddress senderAdress = sender.getSenderAdress();
		 * 
		 * senderAdress.setStreetName(senderDto.getSenderAdress().getStreetName());
		 * senderAdress.setNumber(senderDto.getSenderAdress().getNumber());
		 * senderAdress.setComplement(senderDto.getSenderAdress().getComplement());
		 * senderAdress.setCity(senderDto.getSenderAdress().getCity());
		 * senderAdress.setEstate(senderDto.getSenderAdress().getEstate());
		 * senderAdress.setZipCode(senderDto.getSenderAdress().getZipCode());
		 * senderAdress.setCountry(senderDto.getSenderAdress().getCountry());
		 */
		// sender.setSenderAdress(new SenderAddress());
		// sender.getSenderAdress().setCity("berlandia");
		// sender.getSenderAdress().setSender(sender);

		return repository.save(sender);

	}

}
