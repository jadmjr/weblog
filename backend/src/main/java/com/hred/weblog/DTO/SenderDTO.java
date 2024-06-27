package com.hred.weblog.DTO;

import com.hred.weblog.model.SenderAddress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SenderDTO {

	private String name;
	private String email;
	private String phone;
	private String documentNumber;
	//colocar entidades simples
	private SenderAddress senderAdress;

}
