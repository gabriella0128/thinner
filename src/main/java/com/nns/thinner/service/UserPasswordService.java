package com.nns.thinner.service;

import java.util.Random;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nns.thinner.dto.EmailDto;
import com.nns.thinner.dto.UserPasswordChangeDto;
import com.nns.thinner.dto.UserPasswordTempDto;
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

	private final EmailService emailService;

	public UserPasswordChangeDto.Response changePassword(UserPasswordChangeDto.Request request) {
		UserDto.Info user = userService.findByUserId(request.getUserId());
		if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
			userService.save(user.toBuilder().password(passwordEncoder.encode(request.getNewPassword())).build());
		} else {
			throw new BadCredentialsException(user.getUserId());
		}
		return UserPasswordChangeDto.Response.builder().result(true).reason("SUCCESS").build();

	}

	public UserPasswordTempDto.Response sendTempPassword(UserPasswordTempDto.Request request) {
		UserDto.Info user = userService.findByUserId(request.getUserId());
		String tempPassword = generateTempPassword();
		userService.save(user.toBuilder().password(passwordEncoder.encode(tempPassword)).build());

		emailService.sendEmail(EmailDto.Info.builder()
			.toAddress(user.getEmail())
			.fromAddress("dbwksl128@gmail.com")
			.message(tempPassword)
			.subject("임시 비밀번호")
			.build());

		return UserPasswordTempDto.Response.builder().result(true).reason("SUCCESS").build();
	}

	public String generateTempPassword() {
		int leftLimit = 48;
		int rightLimit = 122;
		int targetStringLength = 10;
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1)
			.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
			.limit(targetStringLength)
			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			.toString();
	}

}
