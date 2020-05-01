package com.learn.online.daos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learn.online.beans.StudentEntity;

@Repository 
public interface StudentEntityDao extends JpaRepository<StudentEntity, Long> {
	
  public Optional<StudentEntity> findByEmail(String email); 
  public Optional<StudentEntity> findByStudentKey(String sudentKey);
}
 