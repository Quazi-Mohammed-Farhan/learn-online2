package com.learn.online.securities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.learn.online.beans.RoleEntity;
import com.learn.online.beans.StudentEntity;

public class UserPrincipal implements UserDetails{

	private static final long serialVersionUID = 1L;

	private StudentEntity studentEntity;
	private String email;
	
	public UserPrincipal(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
		setEmail(studentEntity.getEmail());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		Collection<RoleEntity> rolesEntityList = studentEntity.getRoles();
		
		if(rolesEntityList == null || rolesEntityList.isEmpty()) {
			return grantedAuthorityList;
		}
		
		 studentEntity.getRoles().forEach(roleEntity->{
			
			grantedAuthorityList.add(new SimpleGrantedAuthority(roleEntity.getName()));
			
			roleEntity.getAuthorities().forEach(authority->{
				grantedAuthorityList.add(new SimpleGrantedAuthority(authority.getName()));
			});
		 });
		
		return grantedAuthorityList;
	}

	@Override
	public String getPassword() {
		return studentEntity.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		return studentEntity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
