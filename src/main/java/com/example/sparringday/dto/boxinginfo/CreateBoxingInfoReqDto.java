package com.example.sparringday.dto.boxinginfo;

import com.example.sparringday.util.code.BoxingStyle;
import com.example.sparringday.util.code.FrontHand;
import com.example.sparringday.util.code.SparringIntensity;
import com.example.sparringday.util.code.SparringPurpose;

import lombok.Builder;

@Builder
public record CreateBoxingInfoReqDto(
	Integer yearOfExperience,
	Integer numberOfSparring,
	BoxingStyle style,
	FrontHand frontHand,
	SparringPurpose sparringPurpose,
	SparringIntensity sparringIntensity
) {
}
