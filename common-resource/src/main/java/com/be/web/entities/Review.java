package com.be.web.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


/**
 * The persistent class for the reviews database table.
 * 
 */
@Entity
@Table(name="reviews")
@Setter
@Getter
public class Review extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Lob
	private String comment;

	@Column(name="creator_name")
	private String creatorName;

	@Column(name="parent_id")
	private int parentId;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

}