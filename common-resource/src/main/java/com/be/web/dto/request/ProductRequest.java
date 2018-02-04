package com.be.web.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
	
	private Long id;

	private String color;

	private String fabricOrigin;

	private String fullInformation;

	private String images;

	private String madeIn;

	private String name;

	private double price;

	private double priceCtv1;
	
	private double priceCtv2;
	
	private double priceCtv3;

	private String shortDesc;

	private String size;

	private String specifications;

	private String tags;
	
	private List<Long> categoryIds;
	
}
