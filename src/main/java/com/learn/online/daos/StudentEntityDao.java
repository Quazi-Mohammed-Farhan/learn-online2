package com.learn.online.daos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learn.online.beans.StudentEntity;

@Transactional
@Repository 
public interface StudentEntityDao extends JpaRepository<StudentEntity, Long> {
	
  @Transactional	
  public Optional<StudentEntity> findByEmail(String email); 
  public Optional<StudentEntity> findByStudentKey(String sudentKey);
  
}
 