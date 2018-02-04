package com.be.web.dto.request;

import com.be.web.common.util.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingRequest {
	
	private int currentPage= 1;
	
	private int elementsPerPage = 10;
	
	private String sortField="id";
	
	private String sortType = Constants.ASC;
	
}
