package com.nns.thinner.common.security;

import java.util.Date;
import java.util.Objects;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nns.thinner.common.code.ServiceCode;
import com.nns.thinner.common.exception.CustomException;
import com.nns.thinner.dto.TokenDto;
import com.nns.thinner.service.RedisTemplateService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final RedisTemplateService redisTemplateService;

	private final CustomUserDetailsService customUserDetailsService;

	@Value("${spring.jwt.expire.accessTokenValidMilliSeconds}")
	private long accessTokenValidMilliSeconds;
	@Value("${spring.jwt.expire.refreshTokenValidMilliSeconds}")
	private long refreshTokenValidMilliSeconds;

	/* create token */
	public String createAccessToken(CustomUserDetails userDetails) {

		Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("roles", userDetails.getAuthorities());

		Date now = new Date();

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + accessTokenValidMilliSeconds))
			.signWith(secretKey)
			.compact();
	}

	public String createRefreshToken(CustomUserDetails userDetails) {
		Claims claims = Jwts.claims().setSubject(userDetails.getUserId());

		Date now = new Date();

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + refreshTokenValidMilliSeconds))
			.signWith(secretKey)
			.compact();
	}

	public TokenDto.Info createToken(CustomUserDetails userDetails) {
		String accessToken = createAccessToken(userDetails);
		String refreshToken = createRefreshToken(userDetails);

		redisTemplateService.setData("RT_" + userDetails.getUserId(), refreshToken, refreshTokenValidMilliSeconds);
		return TokenDto.Info.builder().accessToken(accessToken).refreshToken(refreshToken).build();

	}

	/* resolve token */
	public String resolveAccessToken(HttpServletRequest request) {
		return request.getHeader("authorization") != null ? request.getHeader("authorization") : null;
	}

	public String resolveRefreshToken(HttpServletRequest request) {
		return request.getHeader("refreshToken") != null ? request.getHeader("refreshToken") : null;
	}

	public TokenDto.Info resolveToken(HttpServletRequest request) {
		return TokenDto.Info.builder()
			.accessToken(resolveAccessToken(request))
			.refreshToken(resolveRefreshToken(request))
			.build();
	}

	public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
		response.setHeader("authorization", accessToken);
	}

	public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
		response.setHeader("refreshToken", refreshToken);
	}

	public boolean validateToken(String jwtToken) {
		try {
			Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey.getEncoded()).build()
				.parseClaimsJws(jwtToken);

			return !claimsJws.getBody().getExpiration().before(new Date());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public String getUserIdentifyKey(String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	public long getExpirationTime(String accessToken) {
		Date expiration = Jwts.parserBuilder()
			.setSigningKey(secretKey).build()
			.parseClaimsJws(accessToken)
			.getBody()
			.getExpiration();

		long now = new Date().getTime();

		return expiration.getTime() - now;

	}

	public Authentication getAuthentication(String accessToken) {
		CustomUserDetails userDetails
			= customUserDetailsService.loadUserByUsername(getUserIdentifyKey(accessToken));

		if (!Objects.isNull(userDetails)) {
			return new CustomAuthentication(userDetails.getUserId(), userDetails.getPassword(),
				userDetails.getAuthorities());

		}
		throw new CustomException(ServiceCode.USER_NOT_FOUND);
	}

	public void setAuthentication(String accessToken) {
		SecurityContextHolder.getContext().setAuthentication(getAuthentication(accessToken));
	}

	public String reissueToken(String refreshToken) {
		CustomUserDetails userDetails
			= customUserDetailsService.loadUserByUsername(getUserIdentifyKey(refreshToken));

		String key = "RT_" + userDetails.getUserId();

		if (Boolean.TRUE.equals(redisTemplateService.exist(key)) && redisTemplateService.getData(key)
			.equals(refreshToken)) {
			return createAccessToken(userDetails);
		}
		throw new CustomException(ServiceCode.REFRESH_TOKEN_NOT_FOUND);
	}

	public void invalidateToken(String accessToken) {
		if (validateToken(accessToken)) {
			String userId = getUserIdentifyKey(accessToken);
			long expiration = getExpirationTime(accessToken);
			redisTemplateService.setData(accessToken, "logout", expiration);

			if (Boolean.TRUE.equals(redisTemplateService.exist("RT_" + userId))) {
				redisTemplateService.deleteData("RT_" + userId);
			}

			return;
		}
		throw new CustomException(ServiceCode.INVALID_ACCESS_TOKEN);

	}

	public boolean isLoggedOut(String accessToken) {
		return redisTemplateService.exist(accessToken) && redisTemplateService.getData(accessToken).equals("logout");
	}

}
