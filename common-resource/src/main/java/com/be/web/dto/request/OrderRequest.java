package com.be.web.dto.request;

import java.util.List;

import com.be.web.dto.model.OrderProductDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
	
	private List<OrderProductDTO> products;
	
	private Long shipTime; // miliseconds
	
	private String note;
	
}
