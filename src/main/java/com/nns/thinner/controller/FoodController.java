package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.FoodSearchDto;
import com.nns.thinner.service.FoodSearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/food")
@RequiredArgsConstructor
public class FoodController {

	private final FoodSearchService foodSearchService;

	@GetMapping("/search")
	public ResponseEntity<FoodSearchDto.Response> searchFood(@RequestParam String keyword){
		return ResponseEntity.ok().body(foodSearchService.findFoodNameInfo(keyword));

	}



}
