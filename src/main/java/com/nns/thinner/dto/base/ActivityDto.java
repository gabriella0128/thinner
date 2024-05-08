package com.nns.thinner.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ActivityDto {
	@Getter
	@ToString
	@Builder
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ActivityInsertRequest {
		Long exerciseIdx;
		Long minutes;
	}

	@Getter
	@ToString
	@Builder
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ActivityInsertResponse {

	}

}
