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
		private Long exerciseIdx;
		private String activityDt;
		private Long userIdx;
		private Integer minute;
		private String intensity;
		private long version;

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
