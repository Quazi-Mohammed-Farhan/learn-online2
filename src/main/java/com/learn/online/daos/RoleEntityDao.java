package com.learn.online.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.online.beans.RoleEntity;

@Repository
public interface RoleEntityDao extends JpaRepository<RoleEntity, Long>{

	public Optional<RoleEntity> findByName(String name);
}
