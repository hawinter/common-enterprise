package com.be.web.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.be.web.common.util.OrderStatus;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@Setter
@Getter
public class Order extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="order_number")
	private String orderNumber;

	@Column(name="order_status")
	private OrderStatus orderStatus;
	
	private String note;

	@Column(name="order_time")
	private Timestamp orderTime;

	@Column(name="receive_time")
	private Timestamp receiveTime;

	@Column(name="ship_time")
	private Timestamp shipTime;
	
	private int deleted;

	//bi-directional many-to-one association to OrderProduct
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "order_product", 
//		joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), 
//		inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
//	private Set<Product> products;
	
	@OneToMany(mappedBy="order")
	private Set<OrderProduct> orderProducts;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="customer_id")
	private User user;

}