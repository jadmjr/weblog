package com.hred.weblog.DTO;

import com.hred.weblog.model.RecipientAddress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipientDTO {

	private String name;
	private String email;
	private String phone;
	private String documentNumber;
	//colocar entidades simples
	private RecipientAddress recipientAdress;

}
