package com.nns.thinner.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.nns.thinner.common.mapper.GenericMapper;
import com.nns.thinner.dto.base.LogDto;
import com.nns.thinner.entity.LogEntity;

@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface LogMapper extends GenericMapper<LogDto, LogEntity> {

	LogDto.Info toInfoDto(LogEntity logEntity);
	LogEntity fromDtoToEntity(LogDto.Info logDto);

}
