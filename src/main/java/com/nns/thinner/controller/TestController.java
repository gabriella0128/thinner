package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.base.LogDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	@PostMapping("/api/v1/log-test")
	public ResponseEntity<LogDto.Info> testLog() {
		return ResponseEntity.ok().body(LogDto.Info.builder().log("hello").build());
	}

}
