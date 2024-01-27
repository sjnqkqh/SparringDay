package com.example.sparringday.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sparringday.config.CommonException;
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
	// private final Slf4JLogger logger =();

	@Transactional
	public User createNewUser(CreateUserReqDto reqDto) {
		User newUser = User.builder()
			.loginId(reqDto.loginId())
			.encryptedPassword(passwordEncoder.encode(reqDto.password()))
			.build();

		return userRepository.save(newUser);
	}

	@Transactional(readOnly = true)
	public User findUserById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new CommonException(ApiExceptionCode.USER_NOT_EXIST_ERROR));
	}

	@Transactional(readOnly = true)
	protected boolean checkUserLoginIdDuplication(String loginId) {
		return userRepository.existsByLoginIdAndIsDeleted(loginId, false);
	}

	@Transactional(readOnly = true)
	public User login(String loginId, String password) {
		User user = userRepository.findUserByLoginId(loginId)
			.orElseThrow(() -> new CommonException(ApiExceptionCode.USER_NOT_EXIST_ERROR));

		checkPasswordMatch(password, user.getEncryptedPassword());

		return user;
	}

	protected void checkPasswordMatch(String receivedPassword, String storedPassword){
		if (!passwordEncoder.matches(receivedPassword, storedPassword)){
			throw new CommonException(ApiExceptionCode.USER_NOT_EXIST_ERROR);
		}
	}
}
