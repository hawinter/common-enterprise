package com.be.web.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude={"users"})
@EqualsAndHashCode(exclude={"users"}, callSuper=false)
public class Role extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String desc;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private Set<User> users;
	
	//bi-directional many-to-one association to ProductRole
	@OneToMany(mappedBy="role")
	private Set<ProductRole> productRoles;
}
