package com.learn.online.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learn.online.beans.CourseOrderEntity;

@Repository
public interface CourseOrderEntityDao extends JpaRepository<CourseOrderEntity, Long> {
}
