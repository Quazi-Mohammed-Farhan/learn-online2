package com.learn.online.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.online.beans.AuthorityEntity;

@Repository
public interface AuthoritiyEntityDao extends JpaRepository<AuthorityEntity, Long> {

	public Optional<AuthorityEntity> findByName(String name);
}
