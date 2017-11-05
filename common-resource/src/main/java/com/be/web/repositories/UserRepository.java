package com.be.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.be.web.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUserName(String username);

	@Query("select u from User u where u.email = :email")
	List<User> findByEmailReturnStream(@Param("email") String email);
}
