package com.be.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingResponse<T> extends CommonResponse<T> {
	
	private int currentPage;
	
	private int elementsPerPage = 10;
	
	private int totalPages;
	
	private long totalElements;
}
