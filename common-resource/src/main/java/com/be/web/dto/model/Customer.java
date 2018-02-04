package com.be.web.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
	
	private Long id;
	
	private String username;

	private String fullName;
	
	private String role;
	
}
