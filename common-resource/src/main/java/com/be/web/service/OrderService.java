package com.be.web.service;

import com.be.web.dto.request.OrderRequest;
import com.be.web.dto.response.CommonResponse;
import com.be.web.dto.response.OrderResponse;

public interface OrderService {

	CommonResponse<OrderResponse> createOrder(OrderRequest orderRequest);
	
}
