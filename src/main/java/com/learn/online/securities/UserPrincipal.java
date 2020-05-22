package com.learn.online.securities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
		
		Set<GrantedAuthority> grantedAuthoritySet =new HashSet<>();
		Collection<RoleEntity> rolesEntitySet = studentEntity.getRoles();
		
		if(rolesEntitySet == null || rolesEntitySet.isEmpty()) {
			return grantedAuthoritySet;
		}
		
		 studentEntity.getRoles().forEach(roleEntity->{
			
			grantedAuthoritySet.add(new SimpleGrantedAuthority(roleEntity.getName()));
			
			roleEntity.getAuthorities().forEach(authority->{
				grantedAuthoritySet.add(new SimpleGrantedAuthority(authority.getName()));
			});
		 });
		
		return grantedAuthoritySet;
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
