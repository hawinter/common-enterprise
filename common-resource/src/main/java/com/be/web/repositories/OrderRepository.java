package com.be.web.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.be.web.entities.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	
}
