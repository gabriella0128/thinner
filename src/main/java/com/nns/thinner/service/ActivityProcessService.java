package com.nns.thinner.service;

import org.springframework.stereotype.Service;

import com.nns.thinner.service.dtoService.ActivityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityProcessService {

	private final ActivityService activityService;

}
