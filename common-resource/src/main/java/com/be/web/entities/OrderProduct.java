package com.be.web.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the order_product database table.
 * 
 */
@Entity
@Table(name="order_product")
@Getter
@Setter
public class OrderProduct extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;

	@Lob
	private String note;

	private int quantity;
	
	private double price;

	//bi-directional many-to-one association to Order
	@ManyToOne
	private Order order;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

}