package com.example.sparringday.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController("/api/additional-info")
@RequiredArgsConstructor
public class UserAdditionalInfoController {
	@PostMapping("/boxing")
	public void registerBoxingStyleInfo(){
		System.out.println();
	}

	// 회원 복싱 정보 조회
	// 회원 복싱 정보 수정
	// 회원 복싱 정보 삭제
}
