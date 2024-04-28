package com.nns.thinner.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nns.thinner.dto.UserPasswordChangeDto;
import com.nns.thinner.dto.base.UserDto;
import com.nns.thinner.service.dtoService.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserPasswordService {
	private final UserService userService;

	private final PasswordEncoder passwordEncoder;

	public UserPasswordChangeDto.Response changePassword(UserPasswordChangeDto.Request request) {
		UserDto.Info user = userService.findByUserId(request.getUserId());

		userService.save(user.toBuilder().password(passwordEncoder.encode(request.getNewPassword())).build());

		return UserPasswordChangeDto.Response.builder().result(true).reason("SUCCESS").build();

	}
}
