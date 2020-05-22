package com.learn.online.services;

import java.util.Optional;

import com.learn.online.dtos.RoleDto;

public interface RoleService {

	Optional<RoleDto> getRoleByName(String roleName); 
}
