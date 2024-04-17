package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.UserJoinDto;
import com.nns.thinner.dto.UserLoginDto;
import com.nns.thinner.service.UserJoinService;
import com.nns.thinner.service.UserLoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserJoinService userJoinService;

	private final UserLoginService userLoginService;

	@PostMapping("/join")
	public ResponseEntity<UserJoinDto.Response> joinUser(@RequestBody final UserJoinDto.Request request) {
		return ResponseEntity.ok().body(userJoinService.joinUser(request));
	}

	@PostMapping("/login")
	public ResponseEntity<UserLoginDto.Response> loginUser(@RequestBody final UserLoginDto.Request request) {
		return ResponseEntity.ok().body(userLoginService.login(request));
	}

}