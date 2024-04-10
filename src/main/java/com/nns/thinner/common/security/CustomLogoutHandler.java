package com.nns.thinner.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String accessToken = jwtTokenProvider.resolveAccessToken(request);
		jwtTokenProvider.invalidateToken(accessToken);
	}
}
