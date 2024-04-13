package com.nns.thinner.entity;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import com.nns.thinner.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "diet")
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("use_yn = true")
public class DietEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diet_idx")
	private Long dietIdx;

	@JoinColumn(name = "user_idx")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity user;

	@Column(name = "meal", columnDefinition = "LONGTEXT")
	private String meal;

	@Column(name = "diet_dt")
	private LocalDate dietDt;
}
