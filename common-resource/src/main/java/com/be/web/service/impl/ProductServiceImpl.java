package com.be.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.be.web.common.util.Constants;
import com.be.web.dto.request.PagingRequest;
import com.be.web.dto.request.ProductRequest;
import com.be.web.dto.response.CommonResponse;
import com.be.web.dto.response.PagingResponse;
import com.be.web.dto.response.ProductResponse;
import com.be.web.entities.Category;
import com.be.web.entities.Product;
import com.be.web.entities.ProductCategory;
import com.be.web.entities.ProductRole;
import com.be.web.entities.Role;
import com.be.web.entities.User;
import com.be.web.repositories.CategoryRepository;
import com.be.web.repositories.ProductCategoryRepository;
import com.be.web.repositories.ProductRepository;
import com.be.web.repositories.ProductRoleRepository;
import com.be.web.repositories.RoleRepository;
import com.be.web.repositories.UserRepository;
import com.be.web.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ProductRoleRepository productRoleRepository;
	
	
	
	@Override
	public PagingResponse<List<ProductResponse>> getProducts(PagingRequest pagingRequest) {
		
		PagingResponse<List<ProductResponse>> result = new PagingResponse<List<ProductResponse>>();
		
		//new PageRequest(1, 10, new Sort(Sort.Direction.DESC, "description").and(new Sort(Sort.Direction.ASC, "title"));
		Direction sortType = pagingRequest.getSortType().equals(Constants.ASC) ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		Pageable pageable = new PageRequest(pagingRequest.getCurrentPage() - 1, pagingRequest.getElementsPerPage(),
				sortType, pagingRequest.getSortField());
		Page<Product> products = productRepository.findAll(pageable);
		
		List<ProductResponse> productList = new ArrayList<ProductResponse>(); 
		
		for (Product product : products.getContent()) {
			ProductResponse productResponse = new ProductResponse();
			BeanUtils.copyProperties(product, productResponse);
			productList.add(productResponse);
		}
		
		result.setValue(productList);
		result.setTotalPages(products.getTotalPages());
		result.setTotalElements(products.getTotalElements());
		result.setCurrentPage(pagingRequest.getCurrentPage());
		result.setElementsPerPage(pagingRequest.getElementsPerPage());
				
		return result;
	}

	@Override
	@Transactional
	public CommonResponse<ProductResponse> createProduct(ProductRequest productRequest) {
		
		CommonResponse<ProductResponse> result = new CommonResponse<ProductResponse>();
		
		Product product = new Product();
		BeanUtils.copyProperties(productRequest, product);
		
		// Get current user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userRepository.findByUserName(auth.getName());
		
		if(currentUser != null) {
			product.setUser(currentUser);
		}
		
		Product returnProduct = productRepository.saveAndFlush(product);
		
		if(returnProduct == null) {
			result.setResultCode(Constants.FAIL_CODE);
			result.setMessage("Something wrong while saving the product");
			return result;
		}
		
		// Save category
		List<Long> catIds = productRequest.getCategoryIds();
		
		for (Long id : catIds) {
			Category category = categoryRepository.findOne(id);
			ProductCategory productCategory = new ProductCategory();
			
			if(category != null) {
				productCategory.setProduct(product);
				productCategory.setCategory(category);
				
				productCategoryRepository.save(productCategory);
			}
		}
		
		// Save price for each role
		Role roleCTV1 = roleRepository.findByName(Constants.CTV1);
		ProductRole productRoleCTV1 = new ProductRole();
		productRoleCTV1.setProduct(returnProduct);
		productRoleCTV1.setRole(roleCTV1);
		productRoleCTV1.setPrice(productRequest.getPriceCtv1());
		productRoleCTV1.setNote("Price for CTV1");
		
		productRoleRepository.save(productRoleCTV1);
		
		Role roleCTV2 = roleRepository.findByName(Constants.CTV2);
		ProductRole productRoleCTV2 = new ProductRole();
		productRoleCTV2.setProduct(returnProduct);
		productRoleCTV2.setRole(roleCTV2);
		productRoleCTV2.setPrice(productRequest.getPriceCtv2());
		productRoleCTV2.setNote("Price for CTV2");
		
		productRoleRepository.save(productRoleCTV2);
		
		Role roleCTV3 = roleRepository.findByName(Constants.CTV3);
		ProductRole productRoleCTV3 = new ProductRole();
		productRoleCTV3.setProduct(returnProduct);
		productRoleCTV3.setRole(roleCTV3);
		productRoleCTV3.setPrice(productRequest.getPriceCtv3());
		productRoleCTV3.setNote("Price for CTV3");
		
		productRoleRepository.save(productRoleCTV3);
		
		ProductResponse productResponse = new ProductResponse();
		
		BeanUtils.copyProperties(returnProduct, productResponse);
		
		result.setValue(productResponse);
		
		return result;
		
	}

}
