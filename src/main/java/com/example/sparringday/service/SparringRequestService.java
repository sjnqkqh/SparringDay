package com.example.sparringday.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sparringday.config.CommonException;
import com.example.sparringday.entity.SparringRequest;
import com.example.sparringday.entity.User;
import com.example.sparringday.repository.SparringRequestRepository;
import com.example.sparringday.repository.UserRepository;
import com.example.sparringday.util.code.ApiExceptionCode;
import com.example.sparringday.util.code.SparringIntensity;
import com.example.sparringday.util.code.SparringPurpose;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SparringRequestService {
	private final SparringRequestRepository requestRepository;
	private final UserRepository userRepository;

	@Transactional
	public SparringRequest requestSparring(User requester, Long targetUserId, String location, LocalDateTime sparringAt,
		SparringPurpose sparringPurpose, SparringIntensity sparringIntensity) {
		User targetUser = userRepository.findById(targetUserId)
			.orElseThrow(() -> new CommonException(ApiExceptionCode.USER_NOT_EXIST_ERROR));

		SparringRequest newSparringRequest = SparringRequest.builder()
			.requester(requester)
			.targetUser(targetUser)
			.location(location)
			.sparringDatetime(sparringAt)
			.sparringPurpose(sparringPurpose)
			.sparringIntensity(sparringIntensity)
			.build();

		return requestRepository.save(newSparringRequest);
	}
}
