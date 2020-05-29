package com.learn.online.beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/***********************************************************************************************************
 * <h1>RoleEntity!</h1>																					 
 *RoleEntity mapped roles table in DB. It fetches and and store role related information of student 
 *with respect to spring security management using DB. It has many to many association with AuthorityEntity
 *persistent class.
 *                                                                                                                 
 * @author  Quazi Mohammed Farhan Ali.                                                                             
 * @version 1.0           
 * @Purpose PIP Assignment to employee by Cognizant                                                                                           
 * @since   2020-05-29                                                                                                                                                                                                                  
 ************************************************************************************************************/

@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Collection<StudentEntity> studentEntities;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "roles_authorities", joinColumns = {
			@JoinColumn(name = "rol_id", referencedColumnName = "role_id") }, inverseJoinColumns = {
					@JoinColumn(name = "author_id", referencedColumnName = "authority_id") })
	Collection<AuthorityEntity> authorities;

	public RoleEntity() {
	}
	
	public RoleEntity(String roleName) {
		this.name = roleName;
	
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<StudentEntity> getStudentEntities() {
		return studentEntities;
	}

	public void setStudentEntities(Collection<StudentEntity> studentEntities) {
		this.studentEntities = studentEntities;
	}

	public Collection<AuthorityEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<AuthorityEntity> authorities) {
		this.authorities = authorities;
	}

}
