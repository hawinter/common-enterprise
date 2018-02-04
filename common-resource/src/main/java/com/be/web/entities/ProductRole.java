package com.be.web.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the product_role database table.
 * 
 */
@Entity
@Table(name="product_role")
@Getter
@Setter
public class ProductRole extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String note;

	private double price;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

}