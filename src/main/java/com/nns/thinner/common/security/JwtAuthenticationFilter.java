package com.nns.thinner.common.security;

import java.io.IOException;
import java.util.Objects;

import org.springframework.web.filter.GenericFilterBean;

import com.nns.thinner.common.code.ServiceCode;
import com.nns.thinner.common.exception.CustomException;
import com.nns.thinner.dto.TokenDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {

		TokenDto.Info token = jwtTokenProvider.resolveToken((HttpServletRequest)request);

		String accessToken = token.getAccessToken();
		String refreshToken = token.getRefreshToken();

		if (!Objects.isNull(accessToken)) {

			if (jwtTokenProvider.isLoggedOut(accessToken)) {
				throw new CustomException(ServiceCode.ALREADY_LOGGED_OUT);
			}

			if (jwtTokenProvider.validateToken(accessToken)) {
				jwtTokenProvider.setAuthentication(accessToken);
			} else {
				if (Objects.isNull(refreshToken)) {
					throw new CustomException(ServiceCode.INVALID_ACCESS_TOKEN);
				} else {
					if (jwtTokenProvider.validateToken(refreshToken)) {
						String newToken = jwtTokenProvider.reissueToken(refreshToken);
						jwtTokenProvider.setAuthentication(newToken);
						jwtTokenProvider.setHeaderAccessToken((HttpServletResponse)response, newToken);
					} else {
						throw new CustomException(ServiceCode.INVALID_REFRESH_TOKEN);
					}

				}
			}
		}

		chain.doFilter(request, response);

	}

}
