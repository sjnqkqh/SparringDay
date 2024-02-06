package com.example.sparringday.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

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
import com.example.sparringday.util.code.SparringIntensity;
import com.example.sparringday.util.code.SparringPurpose;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Import(TestContainerSettingConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SparringRequestServiceTest {

	@Autowired
	private SparringRequestService sparringRequestService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@Transactional
	void shouldRequestSparring() {
		// Given
		User requesterUser = User.builder().loginId("requester").encryptedPassword("").build();
		User targetUser = User.builder().loginId("target").encryptedPassword("").build();
		userRepository.save(requesterUser);
		userRepository.save(targetUser);

		// When
		SparringRequest sparringRequest = sparringRequestService.requestSparring(requesterUser, targetUser.getId(), "",
			LocalDateTime.now(), SparringPurpose.HOBBY, SparringIntensity.METHOD);

		// Then
		assertThat(sparringRequest).isNotNull();
	}

	@Test
	@Transactional
	void requestSparringTest_실패_삭제된_대상자() {
		// Given
		User requesterUser = User.builder().loginId("requester").encryptedPassword("").build();
		User targetUser = User.builder().loginId("target").encryptedPassword("").isDeleted(true).build();
		userRepository.save(requesterUser);
		userRepository.save(targetUser);
		userRepository.flush();
		entityManager.clear(); // 영속성 컨텍스트 캐시를 초기화하여 service layer에서 Select가 일어나도록 유도

		// When
		Throwable throwable = assertThrows(CommonException.class,
			() -> sparringRequestService.requestSparring(requesterUser, targetUser.getId(), "", LocalDateTime.now(),
				SparringPurpose.HOBBY, SparringIntensity.METHOD));

		// Then
		assertThat(throwable.getMessage()).isEqualTo(ApiExceptionCode.USER_NOT_EXIST_ERROR.msg);
	}
}