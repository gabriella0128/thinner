package com.nns.thinner.common.code;

import lombok.Getter;

@Getter
public enum ServiceCode {
	SUCCESS("TN-0001", "성공"),

	INVALID_ACCESS_TOKEN("TN-4001", "액세스 토큰이 유효하지 않습니다."),

	INVALID_REFRESH_TOKEN("TN-4002", "리프래시 토큰이 유효하지 않습니다."),

	USER_NOT_FOUND("TN-4003", "일치하는 사용자가 없습니다."),

	REFRESH_TOKEN_NOT_FOUND("TN-4004", "해당 리프래시 토큰이 없습니다."),

	ALREADY_LOGGED_OUT("TN-4005", "이미 로그아웃된 사용자입니다.");

	public final String code;
	public final String detail;

	ServiceCode(String code, String detail) {
		this.code = code;
		this.detail = detail;
	}
}
