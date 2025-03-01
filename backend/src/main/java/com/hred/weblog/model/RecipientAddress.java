package com.hred.weblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_recipient_address")
public class RecipientAddress extends Address {
	@OneToOne(mappedBy = "recipientAdress")
	@JsonIgnore
	private Recipient recipient;

}
