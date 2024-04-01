package com.nns.thinner.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "food")
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("use_yn = true")
public class FoodEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_idx")
	private Long foodIdx;

	@Column(name = "food_num")
	private Integer foodNum;

	@Column(name = "food_code")
	private String foodCode;

	@Column(name = "food_name")
	private String foodName;

	@Column(name = "serving_unit")
	private Double servingUnit;

	@Column(name = "serving_size")
	private Double servingSize;

	@Column(name = "food_maker")
	private String foodMaker;

	@Column(name = "kcal")
	private Double kcal;

	@Column(name = "carbohydrate")
	private Double carbohydrate;

	@Column(name = "protein")
	private Double protein;

	@Column(name = "fat")
	private Double fat;

	@Column(name = "sugars")
	private Double sugars;

	@Column(name = "sodium")
	private Double sodium;

	@Column(name = "cholesterol")
	private Double cholesterol;

	@Column(name = "saturated_fat")
	private Double saturatedFat;

	@Column(name = "trans_fat")
	private Double transFat;

	@Column(name = "create_dt")
	private LocalDateTime createDt;

	@Column(name = "modify_dt")
	private LocalDateTime modifyDt;

}
