package com.learn.online.listeners;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.learn.online.beans.AuthorityEntity;
import com.learn.online.beans.RoleEntity;
import com.learn.online.beans.StudentEntity;
import com.learn.online.daos.AuthoritiyEntityDao;
import com.learn.online.daos.CourseEntityDao;
import com.learn.online.daos.RoleEntityDao;
import com.learn.online.daos.StudentEntityDao;
import com.learn.online.enums.SecurityRolesAndAuthorities;
import com.learn.online.utils.CustomUtils;

@Component
public class InititialAppSetListener {

	@Autowired
	private RoleEntityDao roleEntityDao;
	
	@Autowired
	private AuthoritiyEntityDao authorityEntityDao;
	
	@Autowired
	StudentEntityDao studentEntityDao;
	
	@Autowired
	CourseEntityDao courseEntityDao;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@EventListener
	@Transactional
	public void setAdmin(ApplicationReadyEvent appReadEvent) {
		
		AuthorityEntity readAuthorityEntity = 
				createAuthority(SecurityRolesAndAuthorities.READ_AUTHORITY.name());
		
		AuthorityEntity writeAuthorityEntity = 
				createAuthority(SecurityRolesAndAuthorities.WRITE_AUTHORITY.name());
		
		AuthorityEntity deleteAuthorityEntity = 
				createAuthority(SecurityRolesAndAuthorities.DELETE_AUTHORITY.name());
		
		/*
		 * RoleEntity userRoleEntity =
		 * createRole(SecurityRolesAndAuthorities.ROLE_USER.name(),
		 * Arrays.asList(readAuthorityEntity, writeAuthorityEntity));
		 */
		
		RoleEntity adminRoleEntity = createRole(SecurityRolesAndAuthorities.ROLE_ADMIN.name(),
				Arrays.asList(readAuthorityEntity, writeAuthorityEntity,deleteAuthorityEntity));
		
		if(adminRoleEntity == null) {return;} 
		
		//Creating admin entity if not present
		createStudentEntity(Arrays.asList(adminRoleEntity));
		
		
	}

	@Transactional
	public AuthorityEntity createAuthority(String authorityName) {
		
		Optional<AuthorityEntity> dbAuthorityEntity = authorityEntityDao.findByName(authorityName);
		
		if(!dbAuthorityEntity.isPresent()) {
			
			return authorityEntityDao.save(new AuthorityEntity(authorityName));
		} 
		
		return dbAuthorityEntity.orElseGet(()->null);
	}
	
	@Transactional
	public RoleEntity createRole(String roleName, Collection<AuthorityEntity> authorities) {
		
		Optional<RoleEntity> dbRoleEntity = roleEntityDao.findByName(roleName);
		
		if(!dbRoleEntity.isPresent()) {
			
			RoleEntity roleEntity = new RoleEntity(roleName);
			roleEntity.setName(roleName);
			roleEntity.setAuthorities(authorities);
			
			return roleEntityDao.save(roleEntity);
		} 
		
		return dbRoleEntity.orElseGet(()->null);
		
	}

	@Transactional
	private StudentEntity createStudentEntity(List<RoleEntity> rolesList) {

		if(studentEntityDao.findByEmail("admin@gmail.com").isPresent()) {
			return null;
		}
		
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setActive(true);
		studentEntity.setCity("Bangalore");
		studentEntity.setCountry("India");
		studentEntity.setCreationtDate(LocalDate.now());
		studentEntity.setEmail("admin@gmail.com");
		studentEntity.setEncryptedPassword(bCryptPasswordEncoder.encode("AppleRedColor2$"));
		studentEntity.setFirstName("Super");
		studentEntity.setLastName("Admin");
		studentEntity.setPhone("1234567899");
		studentEntity.setRoles(rolesList);
		studentEntity.setState("KA");
		studentEntity.setStudentKey(CustomUtils.getSHA256());
		
		return studentEntityDao.save(studentEntity);
		
	}
	
}
