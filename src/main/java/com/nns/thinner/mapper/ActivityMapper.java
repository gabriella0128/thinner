package com.nns.thinner.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.nns.thinner.common.mapper.GenericMapper;
import com.nns.thinner.dto.base.ActivityDto;
import com.nns.thinner.entity.ActivityEntity;

@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ActivityMapper extends GenericMapper<ActivityDto, ActivityEntity> {

	ActivityEntity map(Long activityIdx);

	@Mapping(source = "user.userIdx", target = "userIdx")
	ActivityDto.Info toInfoDto(ActivityEntity activityEntity);

	@Mapping(source = "userIdx", target = "user.userIdx")
	ActivityEntity fromDtoToEntity(ActivityDto.Info activityDto);

}
