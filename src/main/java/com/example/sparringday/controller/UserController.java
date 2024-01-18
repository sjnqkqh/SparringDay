package com.example.sparringday.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sparringday.dto.BooleanRespDto;
import com.example.sparringday.dto.user.request.CreateUserReqDto;
import com.example.sparringday.dto.user.request.LoginReqDto;
import com.example.sparringday.dto.user.response.AuthenticationResponse;
import com.example.sparringday.service.UserService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/user")
@RestController
@AllArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/sign-up")
	public AuthenticationResponse signUp(@RequestBody CreateUserReqDto reqDto) {
		return userService.createNewUser(reqDto.loginId(), reqDto.password());
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginReqDto reqDto) {
		userService.login(reqDto.loginId(), reqDto.password());
		return "token";
	}
}
