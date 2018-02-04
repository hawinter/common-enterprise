package com.be.web.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@Setter
@Getter
public class Product extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String color;

	@Column(name="fabric_origin")
	private String fabricOrigin;

	@Lob
	@Column(name="full_information")
	private String fullInformation;

	@Lob
	private String images;

	@Column(name="made_in")
	private String madeIn;

	private String name;

	private double price;

	@Lob
	@Column(name="short_desc")
	private String shortDesc;

	private String size;

	@Lob
	private String specifications;

	@Lob
	private String tags;
	
	private int deleted;
	
	private int popular;

	//bi-directional many-to-one association to OrderProduct
//	@ManyToMany(mappedBy="product")
//	private Set<Order> orders;
	
	@OneToMany(mappedBy="product")
	private Set<OrderProduct> orderProducts;

	//bi-directional many-to-one association to ProductRole
	@OneToMany(mappedBy="product")
	private List<ProductRole> productRoles;
	
	@OneToMany(mappedBy="product")
	private Set<ProductCategory> productCategories;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="creator")
	private User user;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="product")
	private List<Review> reviews;

}