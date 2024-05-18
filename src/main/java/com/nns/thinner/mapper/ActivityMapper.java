package com.nns.thinner.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nns.thinner.common.mapper.GenericMapper;
import com.nns.thinner.dto.base.ActivityDto;
import com.nns.thinner.entity.ActivityEntity;

@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ActivityMapper extends GenericMapper<ActivityDto, ActivityEntity> {

	ObjectMapper objectMapper = new ObjectMapper();

	ActivityEntity map(Long activityIdx);

	@Named("activitiesToStringMapping")
	default String map(List<ActivityDto.ActivityItem> activities) throws JsonProcessingException {
		return objectMapper.writeValueAsString(activities);
	}

	@Named("stringToActivitiesMapping")
	default List<ActivityDto.ActivityItem> map(String activities) throws JsonProcessingException {
		return objectMapper.readValue(activities,
			objectMapper.getTypeFactory().constructCollectionType(List.class, ActivityDto.ActivityItem.class));
	}

	@Mapping(target = "activities", source = "activities", qualifiedByName = "stringToActivitiesMapping")
	@Mapping(source = "user.userIdx", target = "userIdx")
	ActivityDto.Info toInfoDto(ActivityEntity activityEntity);

	@Mapping(target = "activities", source = "activities", qualifiedByName = "activitiesToStringMapping")
	@Mapping(source = "userIdx", target = "user.userIdx")
	ActivityEntity fromDtoToEntity(ActivityDto.Info activityDto);

	@Mapping(target = "activities", source = "activities", qualifiedByName = "stringToActivitiesMapping")
	@Mapping(source = "user.userIdx", target = "userIdx")
	List<ActivityDto.Info> toInfoDtoList(List<ActivityEntity> activityEntityList);

}
