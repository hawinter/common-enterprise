package com.be.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.be.web.common.util.Constants;
import com.be.web.dto.request.PagingRequest;
import com.be.web.dto.request.ProductRequest;
import com.be.web.dto.response.CommonResponse;
import com.be.web.dto.response.PagingResponse;
import com.be.web.dto.response.ProductResponse;
import com.be.web.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
    	
    	log.info("API call /product, method POST");
    	
    	CommonResponse<ProductResponse> reponse;
    	
    	try {
    		
			reponse = new CommonResponse<ProductResponse>();
			
			reponse = productService.createProduct(productRequest);
			
		} catch (Exception e) {
			reponse = new CommonResponse<>();
			reponse.setResultCode(Constants.FAIL_CODE);
			reponse.setMessage(e.getMessage());
			log.error("Error while process product creation request", e);
		}
    	
		return reponse;
        
    }
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public CommonResponse<List<ProductResponse>> getProducts(
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "elementsPerPage", required = false) Integer elementsPerPage,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortType", required = false) String sortType) {

		PagingRequest pagingRequest = new PagingRequest();
		if (currentPage != null) {
			pagingRequest.setCurrentPage(currentPage);
		}

		if (elementsPerPage != null) {
			pagingRequest.setElementsPerPage(elementsPerPage);
		}

		if (!StringUtils.isEmpty(sortField)) {
			pagingRequest.setSortField(sortField);
		}

		if (!StringUtils.isEmpty(sortType)) {
			pagingRequest.setSortType(sortType);
		}

		PagingResponse<List<ProductResponse>> reponse = new PagingResponse<List<ProductResponse>>();

		reponse = productService.getProducts(pagingRequest);

		return reponse;

	}
}
