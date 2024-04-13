package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.base.DietDto;
import com.nns.thinner.service.DietCreateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class DietController {

	private final DietCreateService dietCreateService;

	@PostMapping("/diet")
	public ResponseEntity<DietDto.DietInsertResponse> registerDiet(
		@RequestBody final DietDto.DietInsertRequest request) {
		return ResponseEntity.ok().body(dietCreateService.createDiet(request));
	}

}
