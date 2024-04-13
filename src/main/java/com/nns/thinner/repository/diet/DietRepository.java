package com.nns.thinner.repository.diet;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nns.thinner.entity.DietEntity;

import io.lettuce.core.dynamic.annotation.Param;

public interface DietRepository extends JpaRepository<DietEntity, Long> {

	@Query("SELECT d FROM DietEntity d WHERE d.dietDt = :dietDt AND d.user.userIdx = :userIdx")
	Optional<DietEntity> findDietEntityByDietDtAndUser(@Param("dietDt") LocalDate dietDt,
		@Param("userIdx") Long userIdx);

}
