package com.example.sparringday.dto.user.request;

import com.example.sparringday.util.code.FrontHand;
import com.example.sparringday.util.code.SparringIntensity;
import com.example.sparringday.util.code.SparringPurpose;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserBoxingInfoReqDto(
	@Min(0) @Max(100) @NotEmpty
	int yearOfExperience,

	@Min(0)	@Max(250)
	int height,

	@Min(0)	@Max(250)
	int weight,

	@NotNull
	FrontHand frontHand,

	@NotNull
	SparringPurpose sparringPurpose,

	@NotNull
	SparringIntensity sparringIntensity,

	@Min(0)
	int numberOfSparring
) {
}
