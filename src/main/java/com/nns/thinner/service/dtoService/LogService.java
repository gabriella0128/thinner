package com.nns.thinner.service.dtoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nns.thinner.dto.base.LogDto;
import com.nns.thinner.entity.LogEntity;
import com.nns.thinner.mapper.LogMapper;
import com.nns.thinner.repository.LogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogService {

	private final LogRepository logRepository;
	private final LogMapper logMapper;

	@Transactional
	public LogDto.Info save(LogDto.Info logInfo){
		LogEntity log = logMapper.fromDtoToEntity(logInfo);
		logRepository.save(log);
		return logMapper.toInfoDto(log);
	}


}
