package com.learn.online.beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/***********************************************************************************************************
 * <h1>AuthorityEntity!</h1>																					 
 *AuthorityEntity bean mapped to Authorities DB table. I used JPA annotation to map it to db table and 
 *I also associate it with RoleEntity with many to many association. It keep the authorization 
 *information of student
 *                                                                                                                 
 * @author  Quazi Mohammed Farhan Ali.                                                                             
 * @version 1.0           
 * @Purpose PIP Assignment to employee by Cognizant                                                                                           
 * @since   2020-05-29                                                                                                                                                                                                                  
 ************************************************************************************************************/

@Entity
@Table(name = "authorities")
public class AuthorityEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authority_id")
	private Long authorityId;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private Collection<RoleEntity> roleEntities;

	public AuthorityEntity() {
	}
	
	public AuthorityEntity(String authorityName) {
		this.name = authorityName;
	}

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<RoleEntity> getRoleEntities() {
		return roleEntities;
	}

	public void setRoleEntities(Collection<RoleEntity> roleEntities) {
		this.roleEntities = roleEntities;
	}

}
