package com.be.web.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude={"roles"})
@EqualsAndHashCode(exclude={"roles"}, callSuper=false)
public class User extends AbstractEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="user_name")
	private String userName;
	
	private String password;
	
	@Column(name="full_name")
	private String fullName;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private int enabled;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;
	
//	@ManyToMany defines a many-to-many relationship between 2 entities. 
//	@JoinColumn indicates the entity is the owner of the relationship: 
//	the corresponding table has a column with a foreign key to the referenced table. 
//	mappedBy indicates the entity is the inverse of the relationship.
	
//	Cascade Operation 
//
//	Description 
//
//	ALL
//
//	All cascade operations will be applied to the parent entity’s related entity. All is equivalent to specifying cascade={DETACH, MERGE, PERSIST, REFRESH, REMOVE}
//
//	DETACH
//
//	If the parent entity is detached from the persistence context, the related entity will also be detached. 
//
//	MERGE
//
//	If the parent entity is merged into the persistence context, the related entity will also be merged. 
//
//	PERSIST
//
//	If the parent entity is persisted into the persistence context, the related entity will also be persisted. 
//
//	REFRESH
//
//	If the parent entity is refreshed in the current persistence context, the related entity will also be refreshed. 
//
//	REMOVE
//
//	If the parent entity is removed from the current persistence context, the related entity will also be removed. 
	
}
