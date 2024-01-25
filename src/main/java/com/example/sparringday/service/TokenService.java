package com.example.sparringday.service;

import org.springframework.stereotype.Service;

import com.example.sparringday.entity.Token;
import com.example.sparringday.entity.User;
import com.example.sparringday.repository.TokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final TokenRepository tokenRepository;

	public void saveToken(User user, String jwtToken){
		Token token = Token.builder()
			.user(user)
			.token(jwtToken)
			.expired(false)
			.revoked(false)
			.build();

		tokenRepository.save(token);
	}
}
