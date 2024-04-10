package com.nns.thinner.common.security;

import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nns.thinner.dto.base.UserDto;
import com.nns.thinner.service.dtoService.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserService userService;

	@Override
	public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserDto.Info user = userService.findByUserId(userId);
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("userId not found");
		}
		//필요 시 여기서 role setting
		CustomUserDetails userDetails = CustomUserDetails.builder()
			.userId(user.getUserId())
			.password(user.getPassword())
			.build();

		userDetails.setRoles("MEMBER");

		return userDetails;
	}
}
