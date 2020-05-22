package com.learn.online.services.impls;

import java.util.Optional;

import com.learn.online.daos.RoleEntityDao;
import com.learn.online.dtos.RoleDto;
import com.learn.online.exceptions.StudentServiceException;
import com.learn.online.services.RoleService;
import com.learn.online.utils.CustomUtils;

public class RoleServiceImpl implements RoleService {

	RoleEntityDao roleEntityDao;
	
	@Override
	public Optional<RoleDto> getRoleByName(String roleName) {
		return Optional.of(CustomUtils.convertToRoleDto(roleEntityDao.findByName(roleName).orElseThrow(()->
			new StudentServiceException("Role Not Found"))));
	}

}
