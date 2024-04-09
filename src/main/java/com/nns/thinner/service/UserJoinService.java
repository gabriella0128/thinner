package com.nns.thinner.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nns.thinner.common.utils.ThinnerUtils;
import com.nns.thinner.dto.UserJoinDto;
import com.nns.thinner.dto.base.UserDto;
import com.nns.thinner.service.dtoService.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserJoinService {

	private final UserService userService;

	private final PasswordEncoder passwordEncoder;

	public boolean checkUniqueUserId(String userId) {
		Long result = userService.existByUserId(userId);
		return result != 1;
	}

	public String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	public UserJoinDto.Response joinUser(UserJoinDto.Request request) {

		if (checkUniqueUserId(request.getUserId())) {

			UserDto.Info user = UserDto.Info.builder()
				.userId(request.getUserId())
				.userKey(ThinnerUtils.generateUserKey())
				.password(encodePassword(request.getPassword()))
				.validateYn(false)
				.build();

			userService.save(user);

			return UserJoinDto.Response.builder().result(true).reason("SUCCESS").build();

		} else {
			return UserJoinDto.Response.builder().result(false).reason("DUPLICATED ID").build();
		}
	}
}
