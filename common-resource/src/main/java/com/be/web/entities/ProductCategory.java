package com.be.web.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the product_category database table.
 * 
 */
@Entity
@Table(name="product_category")
@Getter
@Setter
public class ProductCategory extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String note;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

}