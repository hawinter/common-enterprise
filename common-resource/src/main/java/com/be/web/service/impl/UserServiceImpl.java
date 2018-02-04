package com.be.web.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.be.web.dto.request.UserData;
import com.be.web.entities.Role;
import com.be.web.entities.User;
import com.be.web.repositories.RoleRepository;
import com.be.web.repositories.UserRepository;
import com.be.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser(UserData user) {
		User newUser = new User();
		
		BeanUtils.copyProperties(user, newUser, "roleIds");
		
		Set<Role> roles = new HashSet<Role>();
		for (Long id : user.getRoleIds()) {
			Role role = roleRepository.findOne(id);
			roles.add(role);
		}
		
		newUser.setRoles(roles);
		
		User insertedUser =  userRepository.save(newUser);
		
		return insertedUser;
	}

	@Override
	public User updateUser(UserData user) {
		User existingUser = userRepository.findOne(user.getId());
		
		if(existingUser == null) {
			return null;
		}
		
		BeanUtils.copyProperties(user, existingUser, "roleIds");
		
		Set<Role> roles = new HashSet<Role>();
		for (Long id : user.getRoleIds()) {
			Role role = roleRepository.findOne(id);
			roles.add(role);
		}
		
		existingUser.setRoles(roles);
		
		User updatedUser =  userRepository.save(existingUser);
		
		return updatedUser;
	}

	@Override
	public User getUser(Long id) {
		User user = userRepository.findOne(id);
		
		return user;
	}

	@Override
	public User getUserByUserName(String username) {
		User user = userRepository.findByUserName(username);
		
		return user;
	}

	@Override
	public List<User> getListUsers() {
		List<User> users = new ArrayList<User>();
		
		for (User user : userRepository.findAll()) {
			users.add(user);
		}
		
		return users;
	}
	
}
