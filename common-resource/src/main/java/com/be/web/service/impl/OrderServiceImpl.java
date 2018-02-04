package com.be.web.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.be.web.common.util.OrderStatus;
import com.be.web.common.util.Util;
import com.be.web.dto.model.Customer;
import com.be.web.dto.model.OrderProductDTO;
import com.be.web.dto.request.OrderRequest;
import com.be.web.dto.response.CommonResponse;
import com.be.web.dto.response.OrderResponse;
import com.be.web.entities.Order;
import com.be.web.entities.OrderProduct;
import com.be.web.entities.Product;
import com.be.web.entities.Role;
import com.be.web.entities.User;
import com.be.web.repositories.OrderProductRepository;
import com.be.web.repositories.OrderRepository;
import com.be.web.repositories.ProductRepository;
import com.be.web.repositories.UserRepository;
import com.be.web.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderProductRepository orderProductRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public CommonResponse<OrderResponse> createOrder(OrderRequest orderRequest) {
		
		CommonResponse<OrderResponse> result = new CommonResponse<OrderResponse>();
		
		Order order = new Order();
		
		order.setOrderNumber(Util.getUUID());
		order.setOrderTime(Util.currentTimestamp());
		order.setOrderStatus(OrderStatus.CREATED);
		order.setShipTime(Util.millisecondToTimestamp(orderRequest.getShipTime()));
		
		// Get current user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userRepository.findByUserName(auth.getName());
		order.setUser(currentUser);
		
		orderRepository.save(order);
		
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrder(order);
		
		List<OrderProductDTO> products = orderRequest.getProducts();
		
		for (OrderProductDTO orderProductDTO : products) {
			Product product = productRepository.findOne(orderProductDTO.getId());
			if(product != null) {
				orderProduct.setProduct(product);
				orderProduct.setQuantity(orderProductDTO.getQuantity());
				orderProduct.setNote(orderProductDTO.getNote());
				
				orderProductRepository.save(orderProduct);
			}
		}
		
		OrderResponse orderResponse = new OrderResponse();
		BeanUtils.copyProperties(order, orderResponse);
		orderResponse.setProducts(products);
		Customer customer = new Customer();
		customer.setId(currentUser.getId());
		customer.setUsername(currentUser.getUserName());
		customer.setFullName(currentUser.getFullName());
		String r = "";
		for (Role role : currentUser.getRoles()) {
			r += role + " ";
		}
		customer.setRole(r);
		orderResponse.setCustomer(customer);
		
		result.setValue(orderResponse);
		
		return result;
	}

}
