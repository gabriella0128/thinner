package com.nns.thinner.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nns.thinner.common.mapper.GenericMapper;
import com.nns.thinner.dto.base.DietDto;
import com.nns.thinner.entity.DietEntity;

@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DietMapper extends GenericMapper<DietDto, DietEntity> {

	ObjectMapper objectMapper = new ObjectMapper();

	DietEntity map(Long dietIdx);

	@Named("mealDtoToStringMapping")
	default String map(DietDto.Meal meal) throws JsonProcessingException {
		return objectMapper.writeValueAsString(meal);
	}

	@Named("stringToMealDtoMapping")
	default DietDto.Meal map(String meal) throws JsonProcessingException {
		return objectMapper.readValue(meal, DietDto.Meal.class);
	}

	@Mapping(source = "user.userIdx", target = "userIdx")
	@Mapping(target = "meal", source = "meal", qualifiedByName = "stringToMealDtoMapping")
	DietDto.Info toInfoDto(DietEntity dietEntity);

	@Mapping(target = "meal", source = "meal", qualifiedByName = "mealDtoToStringMapping")
	@Mapping(source = "userIdx", target = "user.userIdx")
	DietEntity fromInfoToEntity(DietDto.Info dietInfo);

}



