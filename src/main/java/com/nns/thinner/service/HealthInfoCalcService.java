package com.nns.thinner.service;

import org.springframework.stereotype.Service;

import com.nns.thinner.dto.base.HealthDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class HealthInfoCalcService {

	public HealthDto.BmiResponse bmiCalcProcess(Double height, Double weight) {
		Double bmi = weight / Math.sqrt(height / 100);
		return HealthDto.BmiResponse.builder().bmi(bmi).build();
	}

}
