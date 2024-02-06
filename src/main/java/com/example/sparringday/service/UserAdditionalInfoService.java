package com.example.sparringday.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sparringday.config.CommonException;
import com.example.sparringday.dto.boxinginfo.CreateBoxingInfoReqDto;
import com.example.sparringday.entity.User;
import com.example.sparringday.repository.UserBoxingInfoRepository;
import com.example.sparringday.entity.UserBoxingInfo;
import com.example.sparringday.util.code.ApiExceptionCode;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserAdditionalInfoService {
	private final UserBoxingInfoRepository boxingInfoRepository;

	public boolean isBoxingInfoRegistered(Long userId) {
		return boxingInfoRepository.existsByUserId(userId);
	}

	@Transactional
	public UserBoxingInfo createBoxingInfo(User user, CreateBoxingInfoReqDto reqDto) {
		// UserBoxingInfo newBoxingInfo = UserBoxingInfo.fromUserAndDto(user, reqDto);
		// return boxingInfoRepository.save(newBoxingInfo);
		return null;
	}

	@Transactional
	public void updateBoxingInfo(User user, CreateBoxingInfoReqDto reqDto) {
		UserBoxingInfo boxingInfo = boxingInfoRepository.findByUserId(user.getId())
			.orElseThrow(() -> new CommonException(ApiExceptionCode.USER_NOT_EXIST_ERROR));
		boxingInfo.updateFromDto(reqDto);
	}

	public Optional<UserBoxingInfo> getBoxingInfoByUserId(Long userId) {
		return boxingInfoRepository.findByUserId(userId);
	}

}