package com.example.sparringday.util.code;

import org.springframework.http.HttpStatus;

public enum ApiExceptionCode {

	BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001", "Bad Request Exception"),
	REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "E0002", "Method argument validation fail"),
	HEADER_FIELD_EXCEPTION(HttpStatus.BAD_REQUEST, "E0003", "Header setting error"),

	INVALID_LOGIN_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "E0100", "Invalid login request."),
	USER_NOT_EXIST_ERROR(HttpStatus.BAD_REQUEST, "E0101", "The user is not exist"),
	DUPLICATE_LOGIN_ID_EXIST_ERROR(HttpStatus.BAD_REQUEST, "E0102", "The same ID exists"),
	NO_LOGIN_ERROR(HttpStatus.BAD_REQUEST, "E0103", "The member is not logged in."),

	INTERNAL_SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "E9999", "Unknown error");

	public final HttpStatus httpStatus;
	public final String code;
	public final String msg;

	ApiExceptionCode(HttpStatus httpStatus, String code, String msg) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.msg = msg;
	}

}
