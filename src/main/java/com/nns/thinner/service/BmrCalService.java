package com.nns.thinner.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class BmrCalService {
	private static final Double BMR_CONSTANT_FOR_MALE = 66.47;
	private static final Double BMR_CONSTANT_FOR_FEMALE = 655.1;

	private static final Double BMR_HEIGHT_CONSTANT_FOR_MALE = 5.0;
	private static final Double BMR_HEIGHT_CONSTANT_FOR_FEMALE = 1.85;

	private static final Double BMR_WEIGHT_CONSTANT_FOR_MALE = 13.75;
	private static final Double BMR_WEIGHT_CONSTANT_FOR_FEMALE = 9.56;

	private static final Double BMR_AGE_CONSTANT_FOR_MALE = 6.76;
	private static final Double BMR_AGE_CONSTANT_FOR_FEMALE = 4.68;

	public Double bmrCalcProcess(String sex, Double height, Double weight, Integer age) {

		if (sex.equals("M")) {
			return BMR_CONSTANT_FOR_MALE + height * BMR_HEIGHT_CONSTANT_FOR_MALE + weight * BMR_WEIGHT_CONSTANT_FOR_MALE
				- age * BMR_AGE_CONSTANT_FOR_MALE;
		} else if (sex.equals("F")) {
			return BMR_CONSTANT_FOR_FEMALE + height * BMR_HEIGHT_CONSTANT_FOR_FEMALE
				+ weight * BMR_WEIGHT_CONSTANT_FOR_FEMALE - age * BMR_AGE_CONSTANT_FOR_FEMALE;
		}

		return null;
	}

}