package com.nns.thinner.dto.base;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class DietDto {
	@Getter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Info {
		private Long dietIdx;
		private Long userIdx;
		private LocalDate dietDt;
		private Meal meal;
		private long version;
	}

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DietInsertRequest {
		private String dietDt;
		private Long userIdx;
		private Integer mealType;
		private String foodName;
		private Double kcal;
	}

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DietInsertResponse {
		private Boolean result;
		private String reason;
	}

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Meal {
		@Builder.Default
		private List<FoodItem> breakfast = new ArrayList<>();

		@Builder.Default
		private List<FoodItem> lunch = new ArrayList<>();

		@Builder.Default
		private List<FoodItem> dinner = new ArrayList<>();

		@Builder.Default
		private List<FoodItem> extra = new ArrayList<>();

	}

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FoodItem {
		private String foodName;
		private Double kcal;
	}
}
