package com.example.sparringday.service;

import org.springframework.stereotype.Service;

import com.example.sparringday.repository.SparringRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SparringRequestService {
	private final SparringRequestRepository requestRepository;
}
