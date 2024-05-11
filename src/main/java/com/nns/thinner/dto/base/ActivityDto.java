package com.nns.thinner.dto.base;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ActivityDto {
	@Getter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Info {
		private Long activityIdx;
		private Long userIdx;
		private Long exerciseIdx;
		private Long minutes;
		private LocalDate activityDt;
		private List<ActivityItem> activities;
		private long version;
	}

	@Getter
	@ToString
	@Builder
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ActivityInsertRequest {
		Long exerciseIdx;
		Long userIdx;
		Long minutes;
		String intensity;
		long version;

	}

	@Getter
	@ToString
	@Builder
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ActivityInsertResponse {
		private Boolean result;
		private String reason;
	}

	@Getter
	@ToString
	@Builder
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ActivityItem {
		private Long exerciseIdx;
		private Integer minute;
		private String intensity;
		private Double kcal;
	}

}
