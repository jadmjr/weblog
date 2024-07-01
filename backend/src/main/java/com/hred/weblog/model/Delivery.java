package com.hred.weblog.model;

import java.util.Date;

import com.hred.weblog.enums.DeliveryStatus;
import com.hred.weblog.enums.DeliveryTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_delivery")

public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private Date postDate;
	private Date exitDate;
	private Date deliveredDate;
	private Double deliveredFee;
	private DeliveryTypes deliveryTypeId;
	private DeliveryStatus deliveryStatusId;

    @ManyToOne
    @JoinColumn(name="sender_id", referencedColumnName = "id")
    private Sender sender;
    
    @ManyToOne
    @JoinColumn(name="recipient_id", referencedColumnName = "id")
    private Recipient recipient;
    
    @ManyToOne
    @JoinColumn(name="responsible_id", referencedColumnName = "id")
    private Responsible responsible;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pack_id", referencedColumnName = "id")
	private Pack pack;


}
