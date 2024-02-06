package com.example.sparringday.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sparringday.dto.boxinginfo.CreateBoxingInfoReqDto;
import com.example.sparringday.service.UserAdditionalInfoService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController("/api/additional-info")
@RequiredArgsConstructor
public class UserAdditionalInfoController {
	private final UserAdditionalInfoService additionalInfoService;

	@PostMapping("/boxing")
	public void registerBoxingStyleInfo(@RequestBody CreateBoxingInfoReqDto reqDto) {


	}

	// 회원 복싱 정보 조회

	// 회원 복싱 정보 수정
	// 회원 복싱 정보 삭제
}
