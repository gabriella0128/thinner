package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.UserJoinDto;
import com.nns.thinner.dto.UserLoginDto;
import com.nns.thinner.dto.UserPasswordChangeDto;
import com.nns.thinner.service.UserJoinService;
import com.nns.thinner.service.UserLoginService;
import com.nns.thinner.service.UserPasswordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserJoinService userJoinService;

	private final UserLoginService userLoginService;

	private final UserPasswordService userPasswordService;

	@PostMapping("/join")
	public ResponseEntity<UserJoinDto.Response> joinUser(@RequestBody final UserJoinDto.Request request) {
		return ResponseEntity.ok().body(userJoinService.joinUser(request));
	}

	@PostMapping("/login")
	public ResponseEntity<UserLoginDto.Response> loginUser(@RequestBody final UserLoginDto.Request request) {
		return ResponseEntity.ok().body(userLoginService.login(request));
	}

	@PostMapping("/new-password")
	public ResponseEntity<UserPasswordChangeDto.Response> setNewPassword(
		@RequestBody final UserPasswordChangeDto.Request request) {
		return ResponseEntity.ok().body(userPasswordService.changePassword(request));
	}

}
