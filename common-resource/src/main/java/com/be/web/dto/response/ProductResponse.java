package com.be.web.dto.response;

import java.util.List;

import com.be.web.entities.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
	
	private Long id;

	private String color;

	private String fabricOrigin;

	private String fullInformation;

	private String images;

	private String madeIn;

	private String name;

	private double price;

	private String shortDesc;

	private String size;

	private String specifications;

	private String tags;
	
	private List<Category> categories;
	
}
