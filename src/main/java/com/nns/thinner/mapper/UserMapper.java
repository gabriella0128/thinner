package com.nns.thinner.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.nns.thinner.common.mapper.GenericMapper;
import com.nns.thinner.dto.base.UserDto;
import com.nns.thinner.entity.UserEntity;

@Mapper(
	componentModel = "spring",
	unmappedTargetPolicy = ReportingPolicy.IGNORE,
	unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends GenericMapper<UserDto, UserEntity> {

	UserEntity map(Long userIdx);

	UserDto.Info toInfoDto(UserEntity userEntity);

	UserEntity fromInfoToEntity(UserDto.Info userDto);

}
