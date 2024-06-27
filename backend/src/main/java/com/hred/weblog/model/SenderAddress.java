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
@Table(name = "tb_sender_address")
public class SenderAddress extends Address {
	@OneToOne(mappedBy = "senderAdress")
	@JsonIgnore
	private Sender sender;

}
