package com.nns.thinner.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ThinnerUtils {

	public static String generateUserKey() {
		StringBuilder userKey = new StringBuilder();
		String nowLocalDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddmmss"));

		userKey.append(nowLocalDate);

		userKey.append(UUID.randomUUID());

		return userKey.toString();
	}

}
