package com.nns.thinner.repository.food;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nns.thinner.entity.FoodEntity;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {

	@Query("SELECT f FROM FoodEntity f WHERE f.foodName LIKE CONCAT(:keyword,'%')")
	List<FoodEntity> findFoodEntitiesByFoodNameStartingWith(String keyword);

	@Query("SELECT f FROM FoodEntity f WHERE f.foodIdx = :foodIdx")
	Optional<FoodEntity> findFoodByFoodIdx(@Param("foodIdx") Long foodIdx);

}
