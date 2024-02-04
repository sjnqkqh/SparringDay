package com.example.sparringday.service;

import org.springframework.stereotype.Service;

import com.example.sparringday.repository.SparringReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SparringReviewService {
	private final SparringReviewRepository reviewRepository;
}
