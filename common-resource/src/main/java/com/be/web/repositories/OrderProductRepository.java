package com.be.web.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.be.web.entities.OrderProduct;

public interface OrderProductRepository extends PagingAndSortingRepository<OrderProduct, Long> {


}
