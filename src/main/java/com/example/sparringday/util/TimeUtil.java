package com.example.sparringday.util;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeUtil {

	/**
	 * String 문자열이 입력한 날짜 패턴에 부합하는지 확인
	 *
	 * @param strDate 문자열 날짜
	 * @param pattern 날짜 패턴
	 * @return DateTime 변환 가능 여부
	 */
	public static boolean isLocalDateParseable(String strDate, DateTimeFormatter pattern) {
		try {
			LocalDate.parse(strDate, pattern);
		} catch (DateTimeException e) {
			log.error("[MarketPlaceDateTimeUtil.isLocalDateParseable] Can not parseable date. Input date :" + strDate);
			return false;
		}
		return true;
	}

	public static boolean isLocalDateParseable(String strDate) {
		return isLocalDateParseable(strDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	/**
	 * String 문자열이 입력한 날짜 패턴에 부합하는지 확인
	 *
	 * @param strDate 문자열 날짜
	 * @return DateTime 변환 가능 여부
	 */
	public static boolean isLocalDateParseableUseSimpleDateTime(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			Long longdate = Long.parseLong(strDate);
			sdf.format(new Date(longdate));
		} catch (Exception e) {
			log.error("[MarketPlaceDateTimeUtil.isLocalDateParseableUseSimpleDateTime] Can not parseable date. Input date :" + strDate);
			return false;
		}
		return true;
	}

	/**
	 * 한국 시각으로 해당 일자의 자정을 UTC 시각으로 변환하여 반환
	 *
	 * @return UTC 기준. 전 날 15:00
	 */
	public static LocalDateTime getStartOfDayAtSeoulToUTC() {
		ZonedDateTime seoul = ZonedDateTime.of(LocalDate.now().atStartOfDay(), ZoneId.of("Asia/Seoul")); // 한국 기준 자정
		return seoul.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime(); // LocalDateTime으로 반환
	}

	/**
	 * LocalDateTime 형식의 시간을 Long 타입의 시간으로 변경
	 *
	 * @param ldt LocalDateTime 형식의 시간
	 * @return Long 타입의 시간
	 */
	public static Long getLongTime(LocalDateTime ldt) {
		return ldt.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
	}

	public static String truncateToSecond(LocalDateTime ldt){
		return ldt.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

}
