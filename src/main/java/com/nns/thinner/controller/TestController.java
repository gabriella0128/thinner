package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.EmailDto;
import com.nns.thinner.dto.UserPasswordTempDto;
import com.nns.thinner.dto.base.LogDto;
import com.nns.thinner.service.EmailService;
import com.nns.thinner.service.UserPasswordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {

	private final EmailService emailService;

	private final UserPasswordService userPasswordService;

	@PostMapping("/api/v1/log-test")
	public ResponseEntity<LogDto.Info> testLog() {
		return ResponseEntity.ok().body(LogDto.Info.builder().log("hello").build());
	}

	@PostMapping("/api/v1/auth/email-test")
	public ResponseEntity<EmailDto.Response> testEmail(@RequestBody EmailDto.Info emailInfo) {
		return ResponseEntity.ok().body(emailService.sendEmail(emailInfo));
	}

	@PostMapping("/api/v1/auth/temp-pass")
	public ResponseEntity<UserPasswordTempDto.Response> tempPassword(@RequestBody UserPasswordTempDto.Request request) {
		return ResponseEntity.ok().body(userPasswordService.sendTempPassword(request));
	}
}
