package com.nns.thinner.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.nns.thinner.common.mapper.GenericMapper;
import com.nns.thinner.dto.base.FoodDto;
import com.nns.thinner.entity.FoodEntity;

@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FoodMapper extends GenericMapper<FoodDto, FoodEntity> {

	FoodEntity map(Long foodIdx);

	FoodDto.Info toInfoDto(FoodEntity foodEntity);

	FoodEntity fromInfoToEntity(FoodDto.Info foodDto);

	@IterableMapping(elementTargetType = FoodDto.Info.class)
	List<FoodDto.Info> toInfoDto(List<FoodEntity> foodEntity);
}
