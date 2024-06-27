package com.hred.weblog.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_recipient")
public class Recipient extends Person {
	//@OneToOne(mappedBy = "recipient", cascade = CascadeType.ALL)
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	private RecipientAddress recipientAdress;
}
