package com.hred.weblog.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

	private Long id;
	private Date exitDate;
	private Date deliveredDate;
	private int deliveryTypeId;
	private int deliveryStatusId;
	private Long responsibleId;
	private Long senderId;
	private Long recipientId;
	private Long packId;

}
