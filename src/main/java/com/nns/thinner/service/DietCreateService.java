package com.nns.thinner.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.nns.thinner.common.utils.DateTimeUtils;
import com.nns.thinner.dto.base.DietDto;
import com.nns.thinner.service.dtoService.DietService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class DietCreateService {

	private final DietService dietService;

	public DietDto.DietInsertResponse createDiet(DietDto.DietInsertRequest request) {

		LocalDate dietDt = DateTimeUtils.toLocalDate(request.getDietDt());
		DietDto.Info diet = dietService.findByDietDtAndUserIdx(dietDt, request.getUserIdx());
		Integer mealType = request.getMealType();

		DietDto.FoodItem foodItem = DietDto.FoodItem.builder()
			.foodName(request.getFoodName())
			.kcal(request.getKcal()).build();

		if (Objects.isNull(diet)) {

			List<DietDto.FoodItem> dietList = new ArrayList<>();
			dietList.add(foodItem);

			DietDto.Meal meal = switch (mealType) {
				case 1 -> DietDto.Meal.builder().breakfast(dietList).build();
				case 2 -> DietDto.Meal.builder().lunch(dietList).build();
				case 3 -> DietDto.Meal.builder().dinner(dietList).build();
				case 4 -> DietDto.Meal.builder().extra(dietList).build();
				default -> null;
			};

			DietDto.Info dietDto = DietDto.Info.builder()
				.dietDt(dietDt)
				.userIdx(request.getUserIdx())
				.meal(meal)
				.build();

			dietService.save(dietDto);

		} else {
			switch (mealType) {
				case 1 -> diet.getMeal().getBreakfast().add(foodItem);
				case 2 -> diet.getMeal().getLunch().add(foodItem);
				case 3 -> diet.getMeal().getDinner().add(foodItem);
				case 4 -> diet.getMeal().getExtra().add(foodItem);
				default -> {
				}
			}

			dietService.save(diet.toBuilder()
				.meal(diet.getMeal()).build());
		}
		return DietDto.DietInsertResponse.builder().result(true).build();
	}
}
