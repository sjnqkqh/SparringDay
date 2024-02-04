package com.example.sparringday.service;

import org.springframework.stereotype.Service;

import com.example.sparringday.repository.SparringMatchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SparringMatchService {

	private final SparringMatchRepository matchRepository;
}
