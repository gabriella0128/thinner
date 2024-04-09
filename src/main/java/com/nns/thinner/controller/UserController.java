package com.nns.thinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nns.thinner.dto.UserJoinDto;
import com.nns.thinner.service.UserJoinService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UserJoinService userJoinService;

	@PostMapping("/join")
	public ResponseEntity<UserJoinDto.Response> joinUser(@RequestBody final UserJoinDto.Request request) {
		return ResponseEntity.ok().body(userJoinService.joinUser(request));
	}
}
