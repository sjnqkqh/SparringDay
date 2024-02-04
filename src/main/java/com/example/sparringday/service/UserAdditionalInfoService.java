package com.example.sparringday.service;

import org.springframework.stereotype.Service;

import com.example.sparringday.repository.UserBoxingInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAdditionalInfoService {
	private final UserBoxingInfoRepository boxingInfoRepository;
}
