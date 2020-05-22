package com.learn.online.services;

import java.util.Optional;

import com.learn.online.dtos.AuthorityDto;

public interface AuthorityService {
	
	public Optional<AuthorityDto> findByName(String name);

}
