package com.nns.thinner.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nns.thinner.common.utils.DateTimeUtils;
import com.nns.thinner.dto.base.ActivityDto;
import com.nns.thinner.service.dtoService.ActivityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityProcessService {

	private final ActivityService activityService;

	public ActivityDto.ActivityInsertResponse createActivity(ActivityDto.ActivityInsertRequest request) {
		LocalDate activityDt = DateTimeUtils.toLocalDate(request.getActivityDt());
		List<ActivityDto.Info> activityByUserIdx = activityService.findByUserIdx(request.getUserIdx());

		ActivityDto.Info activity = activityByUserIdx.stream()
			.filter(item -> item.getActivityDt().isEqual(activityDt))
			.findFirst()
			.orElse(null);

		return null;
	}
}
