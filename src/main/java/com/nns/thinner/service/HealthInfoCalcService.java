package com.nns.thinner.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class HealthInfoCalcService {

	public Double bmiCalcProcess(Double height, Double weight) {

		return weight / Math.sqrt(height / 100);
	}

}
