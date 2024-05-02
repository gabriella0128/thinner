package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.base.HealthDto;
import com.nns.thinner.service.BmrCalcService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/health")
@RequiredArgsConstructor
public class HealthInfoController {

	private final BmrCalcService bmrCalcService;

	@PostMapping("/bmr")
	public ResponseEntity<HealthDto.Response> getBmr(@RequestBody final HealthDto.Request request) {
		return ResponseEntity.ok()
			.body(HealthDto.Response.builder()
				.bmr(bmrCalcService.bmrCalcProcess(request.getSex(), request.getHeight(), request.getWeight(),
					request.getAge()))
				.build());
	}

}
