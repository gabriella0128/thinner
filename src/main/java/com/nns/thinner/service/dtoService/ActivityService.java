package com.nns.thinner.service.dtoService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nns.thinner.dto.base.ActivityDto;
import com.nns.thinner.entity.ActivityEntity;
import com.nns.thinner.mapper.ActivityMapper;
import com.nns.thinner.repository.Activity.ActivityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {

	private final ActivityRepository activityRepository;

	private final ActivityMapper activityMapper;

	@Transactional
	public ActivityDto.Info save(ActivityDto.Info activity) {
		ActivityEntity activityEntity = activityMapper.fromDtoToEntity(activity);
		activityRepository.save(activityEntity);
		return activity;
	}

	@Transactional(readOnly = true)
	public List<ActivityDto.Info> findByUserIdx(Long userIdx) {
		return activityMapper.toInfoDtoList(activityRepository.findActivityEntityByUser(userIdx));
	}

}
