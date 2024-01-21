package com.example.sparringday.service;

import com.example.sparringday.config.CommonException;
import com.example.sparringday.dto.user.response.AuthenticationResponse;
import com.example.sparringday.entity.User;
import com.example.sparringday.repository.UserRepository;
import com.example.sparringday.util.JwtService;
import com.example.sparringday.util.code.ApiExceptionCode;

import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final UserRepository userRepository;

	@Transactional
	public AuthenticationResponse createNewUser(String loginId, String password) {
		String encryptedPassword = passwordEncoder.encode(password);
		User newUser = User.builder().loginId(loginId).encryptedPassword(encryptedPassword).build();
		User savedUser = userRepository.save(newUser);
		String accessToken = jwtService.generateToken(savedUser);
		String refreshToken = jwtService.generateRefreshToken(savedUser);

		return AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
	}

	@Transactional(readOnly = true)
	public User findUserById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new CommonException(ApiExceptionCode.NON_EXIST_USER_ERROR));
	}

	@Transactional(readOnly = true)
	protected boolean checkUserLoginIdDuplication(String loginId) {
		return userRepository.existsByLoginIdAndIsDeleted(loginId, false);
	}

	@Transactional(readOnly = true)
	public Boolean login(String loginId, String password) {
		User user = userRepository.findUserByLoginIdAndIsDeleted(loginId, false)
			.orElseThrow(() -> new CommonException(ApiExceptionCode.LOGIN_ID_NOT_MATCH_ERROR));
		return passwordEncoder.matches(password, user.getEncryptedPassword());
	}

}
