package com.nns.thinner.repository.food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nns.thinner.entity.FoodEntity;

public interface FoodRepository extends JpaRepository<FoodEntity,Long> {

	List<FoodEntity> findFoodEntitiesByFoodNameStartingWith(String keyword);

}
