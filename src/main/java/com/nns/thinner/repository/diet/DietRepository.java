package com.nns.thinner.repository.diet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nns.thinner.entity.DietEntity;

import io.lettuce.core.dynamic.annotation.Param;

public interface DietRepository extends JpaRepository<DietEntity, Long> {

	@Query("SELECT d FROM DietEntity d WHERE d.user.userIdx = :userIdx")
	List<DietEntity> findDietEntityByUser(@Param("userIdx") Long userIdx);

}
