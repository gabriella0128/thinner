package com.nns.thinner.event;

import org.springframework.stereotype.Component;

import com.nns.thinner.dto.base.LogDto;
import com.nns.thinner.service.dtoService.LogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class LogEventListener {

	private final LogService logService;

	public void onLogEventHandler(LogEventDto logEventDto){
		logService.save(LogDto.Info.builder()
			.traceId(logEventDto.getTraceId())
			.httpMessage(logEventDto.getHttpMessage())
			.httpMethod(logEventDto.getHttpMethod())
			.uri(logEventDto.getUri())
			.log(logEventDto.getLog())
			.userIdx(logEventDto.getLogIdx())
			.userKey(logEventDto.getUserKey())
			.createDt(logEventDto.getCreateDt())
			.build());
	}


}
