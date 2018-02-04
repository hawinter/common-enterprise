package com.be.web.dto.response;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.be.web.dto.model.Customer;
import com.be.web.dto.model.OrderProductDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
	
	private Long id;
	
	private String orderNumber;
	
	private List<OrderProductDTO> products;
	
	private Timestamp orderTime;
	
	private int orderStatus;
	
	private Timestamp shipTime; // expected receive time
	
	private Timestamp receiveTime;
	
	private int status;
	
	private Customer customer;
	
}
