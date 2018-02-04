package com.be.web.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Setter
@Getter
public class Category extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;
	
	private String type;
	
	private String desc;
	
	@OneToMany(mappedBy="category")
	private Set<ProductCategory> productCategories;

}