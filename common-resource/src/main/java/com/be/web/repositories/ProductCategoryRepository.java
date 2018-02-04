package com.be.web.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.be.web.entities.ProductCategory;

public interface ProductCategoryRepository extends PagingAndSortingRepository<ProductCategory, Long> {

	
}
