package com.hred.weblog.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hred.weblog.DTO.DeliveryDTO;
import com.hred.weblog.enums.DeliveryStatus;
import com.hred.weblog.enums.DeliveryTypes;
import com.hred.weblog.model.Delivery;
import com.hred.weblog.model.Pack;
import com.hred.weblog.model.Recipient;
import com.hred.weblog.model.Responsible;
import com.hred.weblog.model.Sender;
import com.hred.weblog.repository.DeliveryRepository;
import com.hred.weblog.repository.PackRepository;
import com.hred.weblog.repository.RecipientRepository;
import com.hred.weblog.repository.ResponsibleRepository;
import com.hred.weblog.repository.SenderRepository;

@Service
public class DeliveryService {

	@Autowired
	private DeliveryRepository repository;
	@Autowired
	private ResponsibleRepository responsible_repository;
	@Autowired
	private SenderRepository sender_repository;
	@Autowired
	private RecipientRepository recipient_repository;
	@Autowired
	private PackRepository pack_repository;

	@Transactional(readOnly = true)
	public Page<Delivery> findAll(Pageable pageable) {
		Page<Delivery> delivery = repository.findAll(pageable);
		return delivery;
	}

	@Transactional(readOnly = true)
	public Delivery findById(Long id) {
		Delivery delivery = repository.findById(id).get();
		return delivery;

	}

	@Transactional
	public Delivery save(DeliveryDTO deliveryDto) {
		Delivery delivery = new Delivery();

		Pack pack = pack_repository.findById(deliveryDto.getPackId()).get();
		Responsible responsible = responsible_repository.findById(deliveryDto.getResponsibleId()).get();
		Sender sender = sender_repository.findById(deliveryDto.getSenderId()).get();
		Recipient recipient = recipient_repository.findById(deliveryDto.getRecipientId()).get();

		Instant instant = Instant.now();
		Date current_date = Date.from(instant);

		delivery.setPostDate(current_date);
		delivery.setExitDate(deliveryDto.getExitDate());
		delivery.setDeliveredDate(deliveryDto.getDeliveredDate());

		if (deliveryDto.getDeliveryTypeId() == 1)
			delivery.setDeliveryTypeId(DeliveryTypes.SHIP);
		else if (deliveryDto.getDeliveryTypeId() == 2) {
			delivery.setDeliveryTypeId(DeliveryTypes.TRUCK);
		}

		delivery.setDeliveryStatusId(DeliveryStatus.POSTED);

		float cubicMeter = pack.getCubicMeter();

		if (delivery.getDeliveryTypeId() == DeliveryTypes.SHIP)
			delivery.setDeliveredFee((double) (cubicMeter * 22));
		else if (delivery.getDeliveryTypeId() == DeliveryTypes.TRUCK)
			delivery.setDeliveredFee((double) (cubicMeter * 35));

		delivery.setPack(pack);
		delivery.setResponsible(responsible);
		delivery.setSender(sender);
		delivery.setRecipient(recipient);

		return repository.save(delivery);
	}

}
