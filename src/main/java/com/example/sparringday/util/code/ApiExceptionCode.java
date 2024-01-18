package com.example.sparringday.util.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ApiExceptionCode {

	BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001", "Bad Request Exception"),
	REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "E0002", "Method argument validation fail"),
	HEADER_FIELD_EXCEPTION(HttpStatus.BAD_REQUEST, "E0003", "Header setting error"),

	LOGIN_ID_NOT_MATCH_ERROR(HttpStatus.BAD_REQUEST, "E0100", "No user exists that matches the login ID."),

	INTERNAL_SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "E9999","Unknown error");

	private final HttpStatus httpStatus;
	private final String code;
	private final String msg;

	ApiExceptionCode(HttpStatus httpStatus, String code, String msg) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.msg = msg;
	}

	}
