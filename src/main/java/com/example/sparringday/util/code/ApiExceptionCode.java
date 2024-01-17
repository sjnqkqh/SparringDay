package com.example.sparringday.util.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ApiExceptionCode {

	BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001", "Bad Request Exception"),
	REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "E0002", "Method argument validation fail"),
	HEADER_FIELD_EXCEPTION(HttpStatus.BAD_REQUEST, "E0003", "Header setting error"),

	NO_AUTHORITY_ERROR(HttpStatus.BAD_REQUEST, "E0100", "User have not authority"),
	ASSOCIATION_NOT_MATCH_ERROR(HttpStatus.BAD_REQUEST, "E0100", "Association ID is not matched"),

	SEASON_ENROLLMENT_ALREADY_ONGOING_ERROR(HttpStatus.BAD_REQUEST, "E0201", "Enrollment is already ongoing."),
	SEASON_ENROLLMENT_ALREADY_CLOSED_ERROR(HttpStatus.BAD_REQUEST, "E0202", "This enrollment is closed."),
	SEASON_ENROLLMENT_NOT_START_ERROR(HttpStatus.BAD_REQUEST, "E203", "This season is not start yet"),
	SEASON_ENROLLMENT_PERIOD_ERROR(HttpStatus.BAD_REQUEST, "E204", "Start and close dates are out of order."),
	SEASON_ALREADY_CONFIRMED_ERROR(HttpStatus.BAD_REQUEST, "E205", "This season already confirmed"),
	SEASON_UNCONFIRMED_ERROR(HttpStatus.BAD_REQUEST, "E206", "This season is not confirmed"),

	DUPLICATED_SUBJECT_ERROR(HttpStatus.BAD_REQUEST, "E301", "This subject code is duplicated."),
	DELETED_SUBJECT_ERROR(HttpStatus.BAD_REQUEST, "E302", "This subject already deleted."),

	DUPLICATE_STUDENT_IDENTITY_CODE_ERROR(HttpStatus.BAD_REQUEST, "E401", "This student identity code is duplicated."),

	ENROLLMENT_FAIL_BY_PREVIOUS_GOOD_SCORE_ERROR(HttpStatus.BAD_REQUEST, "E501", "This student previously got more than B in the same subject."),
	ENROLLMENT_ALREADY_EXIST_ERROR(HttpStatus.BAD_REQUEST, "E501", "Enrollment is already exist."),


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
