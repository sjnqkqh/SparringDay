package com.example.sparringday.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sparringday.dto.user.response.AuthenticationResponse;
import com.example.sparringday.entity.Token;
import com.example.sparringday.entity.User;
import com.example.sparringday.repository.TokenRepository;
import com.example.sparringday.util.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final TokenRepository tokenRepository;
	private final JwtService jwtService;

	@Transactional
	public AuthenticationResponse saveToken(User user){
		String jwtToken = jwtService.generateToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);
		Token token = Token.builder()
			.user(user)
			.token(jwtToken)
			.expired(false)
			.revoked(false)
			.build();

		tokenRepository.save(token);
		return AuthenticationResponse.builder()
			.accessToken(jwtToken)
			.refreshToken(refreshToken)
			.build();
	}
}
