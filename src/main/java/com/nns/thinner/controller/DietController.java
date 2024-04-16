package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.base.DietDto;
import com.nns.thinner.service.DietProcessService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class DietController {

	private final DietProcessService dietProcessService;

	@PostMapping("/diet")
	public ResponseEntity<DietDto.DietInsertResponse> registerDiet(
		@RequestBody final DietDto.DietInsertRequest request) {
		return ResponseEntity.ok().body(dietProcessService.createDiet(request));
	}

	@DeleteMapping("/diet")
	public ResponseEntity<DietDto.DietDeleteResponse> deleteDiet(@RequestBody final DietDto.DietDeleteRequest request) {
		return ResponseEntity.ok().body(dietProcessService.deleteDiet(request));
	}

}
