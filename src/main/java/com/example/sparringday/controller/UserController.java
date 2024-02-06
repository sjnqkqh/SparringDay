package com.example.sparringday.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sparringday.dto.user.request.CreateUserReqDto;
import com.example.sparringday.dto.user.request.LoginReqDto;
import com.example.sparringday.dto.user.response.AuthenticationResponse;
import com.example.sparringday.entity.User;
import com.example.sparringday.service.TokenService;
import com.example.sparringday.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RequestMapping("/api/user")
@RestController
@AllArgsConstructor
public class UserController {
	private final UserService userService;
	private final TokenService tokenService;

	@PostMapping("/sign-up")
	public AuthenticationResponse signUp(@RequestBody @Valid CreateUserReqDto reqDto) {
		User user = userService.createNewUser(reqDto);

		return tokenService.saveToken(user);
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginReqDto reqDto) {
		User user = userService.login(reqDto.loginId(), reqDto.password());

		return tokenService.saveToken(user);
	}

	// 회원 신체 정보 갱신

	// 회원 기본 정보 조회

	// 스파링 상대 찾기 플래그 ON

	// 스파링 상대 찾기 플래그 OFF

	// 로그아웃
}
