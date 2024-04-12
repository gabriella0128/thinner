package com.nns.thinner.dto.base;

import java.time.LocalDateTime;
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
		private LocalDateTime dietDt;
		private Meal meal;
	}

	@Getter
	@ToString
	@Builder(toBuilder = true)
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class DietInsertRequest {
		private LocalDateTime dietDt;
		private Long userIdx;
		private Integer mealType;
		private String foodName;
		private String kcal;
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
		private List<FoodItem> breakfast;
		private List<FoodItem> lunch;
		private List<FoodItem> dinner;
		private List<FoodItem> extra;

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
