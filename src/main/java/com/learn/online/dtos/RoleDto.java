package com.learn.online.dtos;

import java.io.Serializable;
import java.util.Collection;

public class RoleDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long roleId;
	private String roleName;
	private Collection<AuthorityDto> authorities;
	private Collection<StudentDto> students;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Collection<AuthorityDto> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<AuthorityDto> authorities) {
		this.authorities = authorities;
	}

	public Collection<StudentDto> getStudents() {
		return students;
	}

	public void setStudents(Collection<StudentDto> students) {
		this.students = students;
	}

}
