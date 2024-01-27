package com.example.sparringday.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sparringday.dto.BooleanRespDto;
import com.example.sparringday.dto.user.request.CreateUserReqDto;
import com.example.sparringday.dto.user.request.LoginReqDto;
import com.example.sparringday.dto.user.response.AuthenticationResponse;
import com.example.sparringday.entity.User;
import com.example.sparringday.service.TokenService;
import com.example.sparringday.service.UserService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/user")
@RestController
@AllArgsConstructor
public class UserController {
	private final UserService userService;
	private final TokenService tokenService;

	@PostMapping("/sign-up")
	public AuthenticationResponse signUp(@RequestBody CreateUserReqDto reqDto) {
		User user = userService.createNewUser(reqDto);

		return tokenService.saveToken(user);
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginReqDto reqDto) {
		User user = userService.login(reqDto.loginId(), reqDto.password());

		return tokenService.saveToken(user);
	}

	@GetMapping("/auth-test")
	public String authTest() {
		return "Boo";
	}
}
