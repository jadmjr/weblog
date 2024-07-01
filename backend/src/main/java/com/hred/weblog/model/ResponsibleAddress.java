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
@Table(name = "tb_responsible_address")
public class ResponsibleAddress extends Address {
	@OneToOne(mappedBy = "responsibleAdress")
	@JsonIgnore
	private Responsible responsible;

}
