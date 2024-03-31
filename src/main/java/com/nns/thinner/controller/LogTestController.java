package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.base.LogDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LogTestController {
	@PostMapping("/api/v1/test")
	public ResponseEntity<LogDto.Info> test(){
		return ResponseEntity.ok().body(LogDto.Info.builder().log("hello").build());
	}
}
