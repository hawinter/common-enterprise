package com.be.web.service;

import java.util.List;

import com.be.web.dto.UserData;
import com.be.web.entities.User;

public interface UserService {

	User createUser(UserData user);

	User updateUser(UserData user);

	User getUser(Long id);

	User getUserByUserName(String username);

	List<User> getListUsers();

}
