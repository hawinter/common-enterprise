package com.be.web.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDTO {
	
	private Long id;
	
	private String name;
	
	private int quantity;
	
	private String note;
	
}
