package com.nns.thinner.dto.base;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class FoodDto {
	@Getter
	@ToString
	@Builder
	@EqualsAndHashCode
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Info {
		private Long foodIdx;
		private String foodCode;
		private Integer foodNum;
		private String foodName;
		private Double servingUnit;
		private Double servingSize;
		private String foodMaker;
		private Double kcal;
		private Double carbohydrate;
		private Double protein;
		private Double fat;
		private Double sugars;
		private Double sodium;
		private Double cholesterol;
		private Double saturatedFat;
		private Double transFat;
		private LocalDateTime createDt;
		private LocalDateTime modifyDt;

	}
}
