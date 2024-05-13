package com.nns.thinner.service.dtoService;

import org.springframework.stereotype.Service;

import com.nns.thinner.mapper.ActivityMapper;
import com.nns.thinner.repository.Activity.ActivityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {

	private final ActivityRepository activityRepository;

	private final ActivityMapper activityMapper;

}
