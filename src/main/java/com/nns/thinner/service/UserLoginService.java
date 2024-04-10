package com.nns.thinner.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nns.thinner.common.security.CustomUserDetails;
import com.nns.thinner.common.security.CustomUserDetailsService;
import com.nns.thinner.common.security.JwtTokenProvider;
import com.nns.thinner.dto.TokenDto;
import com.nns.thinner.dto.UserLoginDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserLoginService {

	private final CustomUserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncoder;

	private final JwtTokenProvider jwtTokenProvider;

	public UserLoginDto.Response login(UserLoginDto.Request request) {
		String userId = request.getUserId();
		String password = request.getPassword();

		CustomUserDetails userDetails = userDetailsService.loadUserByUsername(userId);

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException(userId);
		}
		TokenDto.Info token = jwtTokenProvider.createToken(userDetails);

		return UserLoginDto.Response.builder().result(true).token(token).reason("SUCCESS").build();
	}

}
