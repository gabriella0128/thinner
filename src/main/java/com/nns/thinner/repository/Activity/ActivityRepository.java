package com.nns.thinner.repository.Activity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nns.thinner.entity.ActivityEntity;

import io.lettuce.core.dynamic.annotation.Param;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

	@Query("SELECT a FROM ActivityEntity a WHERE a.user.userIdx = :userIdx")
	List<ActivityEntity> findActivityEntityByUser(@Param("userIdx") Long userIdx);
}
