package com.nns.thinner.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LogEventPublisher {

	private ApplicationEventPublisher applicationEventPublisher;

	public void createLog(LogEventDto logEventDto){
		applicationEventPublisher.publishEvent(logEventDto);
	}

}
