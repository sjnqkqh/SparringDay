package com.example.sparringday.util;

import java.security.SecureRandom;
import java.util.Random;

public class StringUtil {
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
	private static final char DASH = '-';

	public static String generateRandomStringWithDash(int length) {
		Random random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			if (i > 0 && i % 4 == 0) {
				sb.append(DASH);
			}
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}
		return sb.toString();
	}
}
