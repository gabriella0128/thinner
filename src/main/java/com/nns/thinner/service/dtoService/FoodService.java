package com.nns.thinner.service.dtoService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nns.thinner.dto.base.FoodDto;
import com.nns.thinner.mapper.FoodMapper;
import com.nns.thinner.repository.food.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {

	private final FoodRepository foodRepository;
	private final FoodMapper foodMapper;

	@Transactional(readOnly = true)
	public List<FoodDto.Info> findAllByFoodNameStartingWith(String foodName) {
		return foodMapper.toInfoDto(foodRepository.findFoodEntitiesByFoodNameStartingWith(foodName));
	}




}
