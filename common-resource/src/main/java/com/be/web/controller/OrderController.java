package com.be.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.be.web.dto.request.OrderRequest;
import com.be.web.dto.response.CommonResponse;
import com.be.web.dto.response.OrderResponse;
import com.be.web.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
		
    	CommonResponse<OrderResponse> reponse = new CommonResponse<OrderResponse>();
    	
    	reponse = orderService.createOrder(orderRequest);
    	
    	return reponse;
        
    }
	
	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<OrderResponse> updateOrder(@PathVariable Long id) {
		
    	CommonResponse<OrderResponse> reponse = new CommonResponse<OrderResponse>();
    	
    	// TODO
    	
    	return reponse;
        
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<OrderResponse> updateOrder(@RequestBody OrderRequest orderRequest) {
		
    	CommonResponse<OrderResponse> reponse = new CommonResponse<OrderResponse>();
    	
    	// TODO
    	
    	return reponse;
        
    }
	
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<OrderResponse> getOrder() {
		
    	CommonResponse<OrderResponse> reponse = new CommonResponse<OrderResponse>();
    	
    	// TODO
    	
    	return reponse;
        
    }
	
}
