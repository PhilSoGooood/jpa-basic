package jpabook.jpashop.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery {

	@Id @GeneratedValue
	private Long id;

	@Embedded
	private Address address;
	private DeliveryStatus status;

	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
	private Order order;
}
