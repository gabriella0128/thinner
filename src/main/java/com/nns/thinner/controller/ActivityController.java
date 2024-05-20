package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.base.ActivityDto;
import com.nns.thinner.service.ActivityProcessService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {
	private final ActivityProcessService activityProcessService;

	@PostMapping
	public ResponseEntity<ActivityDto.ActivityInsertResponse> registerActivity(
		@RequestBody final ActivityDto.ActivityInsertRequest request) {
		return ResponseEntity.ok().body(activityProcessService.createActivity(request));
	}
}
