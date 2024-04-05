package com.nns.thinner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nns.thinner.dto.FoodSearchDto;
import com.nns.thinner.dto.base.FoodDto;
import com.nns.thinner.service.dtoService.FoodService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class FoodSearchService {

	private final FoodService foodService;

	public FoodSearchDto.Response findFoodNameInfo(String keyword) {

		List<FoodDto.Info> foodNameStartingWith = foodService.findAllByFoodNameStartingWith(keyword);
		List<FoodSearchDto.Info> infos = foodNameStartingWith.stream()
			.map(foodInfo -> FoodSearchDto.Info.builder()
				.foodIdx(foodInfo.getFoodIdx())
				.foodName(foodInfo.getFoodName())
				.kcal(foodInfo.getKcal())
				.build()).toList();
		return FoodSearchDto.Response.builder().result(true).list(infos).build();
	}

	public FoodDto.Info findFoodDetailInfo(Long foodIdx) {
		return foodService.findFoodByFoodIdx(foodIdx);
	}

}
