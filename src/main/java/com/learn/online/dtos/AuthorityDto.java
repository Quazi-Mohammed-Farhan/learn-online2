package com.learn.online.dtos;

import java.io.Serializable;
import java.util.Collection;

import javax.management.relation.Role;

public class AuthorityDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long AuthorityId;
	private String name;
	private Collection<Role> roles;

	public Long getAuthorityId() {
		return AuthorityId;
	}

	public void setAuthorityId(Long authorityId) {
		AuthorityId = authorityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}
