package com.nns.thinner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class UserPasswordTempDto {
	@Getter
	@ToString
	@Builder
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private Boolean result;
		private String reason;
	}

	@Getter
	@ToString
	@Builder
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Request {
		private String userId;
	}

}
