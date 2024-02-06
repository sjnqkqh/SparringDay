package com.example.sparringday.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sparringday.config.CommonException;
import com.example.sparringday.dto.user.request.UserBoxingInfoReqDto;
import com.example.sparringday.dto.user.request.CreateUserReqDto;
import com.example.sparringday.entity.User;
import com.example.sparringday.repository.UserRepository;
import com.example.sparringday.util.code.ApiExceptionCode;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Transactional
	public User createNewUser(CreateUserReqDto reqDto) {
		checkUserLoginIdDuplication(reqDto.loginId());
		User newUser = User.builder()
			.loginId(reqDto.loginId())
			.encryptedPassword(passwordEncoder.encode(reqDto.password()))
			.build();

		return userRepository.save(newUser);
	}

	@Transactional(readOnly = true)
	public User login(String loginId, String password) {
		User user = userRepository.findUserByLoginId(loginId)
			.orElseThrow(() -> new CommonException(ApiExceptionCode.USER_NOT_EXIST_ERROR));

		checkPasswordMatch(password, user.getEncryptedPassword());

		return user;
	}

	@Transactional
	public void updateUserBoxingInfo(User user, UserBoxingInfoReqDto reqDto){

	}

	protected void checkUserLoginIdDuplication(String loginId) {
		if (userRepository.existsByLoginId(loginId)) {
			throw new CommonException(ApiExceptionCode.DUPLICATE_LOGIN_ID_EXIST_ERROR);
		}
	}

	@Transactional(readOnly = true)
	public User findUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> {
			log.error("[UserService.findUserById] No user exist. userId:" + userId);
			return new CommonException(ApiExceptionCode.USER_NOT_EXIST_ERROR);
		});
	}

	private void checkPasswordMatch(String receivedPassword, String storedPassword) {
		if (!passwordEncoder.matches(receivedPassword, storedPassword)) {
			throw new CommonException(ApiExceptionCode.USER_NOT_EXIST_ERROR);
		}
	}
}
