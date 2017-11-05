package com.be.web.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData {
	
	private Long id;
	
	private String userName;
	
	private String password;
	
	private String fullName;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private int enabled;
	
	private List<Long> roleIds;
}
