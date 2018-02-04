package com.be.web.service;

import java.util.List;

import com.be.web.dto.request.PagingRequest;
import com.be.web.dto.request.ProductRequest;
import com.be.web.dto.response.CommonResponse;
import com.be.web.dto.response.PagingResponse;
import com.be.web.dto.response.ProductResponse;

public interface ProductService {
	
	public PagingResponse<List<ProductResponse>> getProducts(PagingRequest pagingRequest);

	public CommonResponse<ProductResponse> createProduct(ProductRequest productRequest);
	
}
