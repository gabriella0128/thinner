package com.nns.thinner.common.code;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CommonCode {
	TABLE_ROW_CREATE_NORMAL("ROW-CREATE-001", 1, "SERVICE_CREATE", "서비스 내부 생성 코드"),
	TABLE_ROW_UPDATE_NORMAL("ROW-UPDATE-001", 1, "SERVICE_UPDATE", "서비스 내부 수정 코드");

	private final String code;
	private final Integer rawCode;
	private final String name;
	private final String detail;

	CommonCode(String code, Integer rawCode, String name, String detail) {
		this.code = code;
		this.rawCode = rawCode;
		this.name = name;
		this.detail = detail;
	}



}
