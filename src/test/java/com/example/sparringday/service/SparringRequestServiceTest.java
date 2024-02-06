package com.example.sparringday.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import com.example.sparringday.config.CommonException;
import com.example.sparringday.config.TestContainerSettingConfig;
import com.example.sparringday.entity.SparringRequest;
import com.example.sparringday.entity.User;
import com.example.sparringday.repository.UserRepository;
import com.example.sparringday.util.code.ApiExceptionCode;

@SpringBootTest
@Import(TestContainerSettingConfig.class)
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SparringRequestServiceTest {

	@Autowired
	private SparringRequestService sparringRequestService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void shouldRequestSparring() {
		// Given
		User requesterUser = User.builder().loginId("requester").encryptedPassword("").build();
		User targetUser = User.builder().loginId("target").encryptedPassword("").build();
		userRepository.save(requesterUser);
		userRepository.save(targetUser);

		// When
		SparringRequest sparringRequest = sparringRequestService.requestSparring(requesterUser, targetUser.getId());

		// Then
		assertThat(sparringRequest).isNotNull();
	}

	@Test
	void requestSparringTest_삭제된_대상자() {
		// Given
		User requesterUser = User.builder().loginId("requester").encryptedPassword("").build();
		User targetUser = User.builder().loginId("target").encryptedPassword("").isDeleted(true).build();
		userRepository.save(requesterUser);
		userRepository.save(targetUser);

		// When
		Throwable throwable = assertThrows(CommonException.class,
			() -> sparringRequestService.requestSparring(requesterUser, targetUser.getId()));

		// Then
		assertThat(throwable.getMessage()).isEqualTo(ApiExceptionCode.USER_NOT_EXIST_ERROR.msg);
	}
}