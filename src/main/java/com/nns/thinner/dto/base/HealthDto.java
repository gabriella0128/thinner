package com.nns.thinner.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class HealthDto {
	@Getter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Request {
		private String sex;
		private Double height;
		private Double weight;
		private Integer age;

	}

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private Double bmr;

	}

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Info {
		private Long healthIdx;
		private Double bmr;
		private Double height;
		private Double weight;
		private Integer age;
		private long version;
	}

}
