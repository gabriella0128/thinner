package com.nns.thinner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class UserPasswordChangeDto {
	@Getter
	@ToString
	@Builder
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Request {
		private String userId;
		private String newPassword;
	}

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
}
