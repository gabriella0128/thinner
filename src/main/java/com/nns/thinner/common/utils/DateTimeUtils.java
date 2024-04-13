package com.nns.thinner.common.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {

	public static final String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyyMMddHHmmss";

	public static final String YEAR_MONTH_DAY = "yyyyMMdd";

	public static final String HOUR_MINUTE_SECOND = "HHmmss";

	public static String nowLocalDate() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	}

	public static Date nowDate() {
		return new Date();
	}

	public static String nowDateTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND));
	}

	public static String tomorrowDate() {
		return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyMMdd"));
	}

	public static LocalDateTime yesterdayDateTime() {
		return LocalDateTime.now().minusDays(1);
	}

	public static LocalDateTime toLocalDateTime(String dateTime) {
		return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND));
	}

	public static String toLocalDateString(LocalDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public static String toIsoLocalDateString(LocalDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND));
	}

	public static long getDurationMinutes(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		return Duration.between(startDateTime, endDateTime).toMinutes();
	}

	public static long getDurationSeconds(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		return Duration.between(startDateTime, endDateTime).toSeconds();
	}

	public static boolean isValidDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		LocalDateTime now = LocalDateTime.now();
		return !now.isBefore(startDateTime) && !now.isAfter(endDateTime);
	}

	public static LocalDate toLocalDate(String dateString) {
		return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(YEAR_MONTH_DAY));
	}

	public static LocalTime toLocalTime(String dateString) {
		return LocalTime.parse(dateString, DateTimeFormatter.ofPattern(HOUR_MINUTE_SECOND));
	}

}
