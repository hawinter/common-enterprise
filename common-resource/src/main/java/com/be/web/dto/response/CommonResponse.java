package com.be.web.dto.response;

import com.be.web.common.util.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse<T> {
	
	protected int resultCode = Constants.SUCCESS_CODE;
	
	protected String message = Constants.SUCCESS_MESSAGE;
	
	protected T value;
	
}
