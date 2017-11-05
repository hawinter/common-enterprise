package com.be.web.dto;

import com.be.web.util.Constant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse<T> {
	
	private int resultCode = Constant.SUCCESS_CODE;
	
	private String message = Constant.SUCCESS_MESSAGE;
	
	private T value;
	
}
