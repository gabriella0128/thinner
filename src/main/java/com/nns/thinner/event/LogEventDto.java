package com.nns.thinner.event;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LogEventDto {
	private Long logIdx;
	private String traceId;
	private String httpMessage;
	private String httpMethod;
	private String uri;
	private String log;
	private Long userIdx;
	private String userKey;
	private LocalDateTime createDt;

}
