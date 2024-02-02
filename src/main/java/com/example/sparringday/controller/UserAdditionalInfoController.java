package com.example.sparringday.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController("/api/additional-info")
@RequiredArgsConstructor
public class UserAdditionalInfoController {
	@PostMapping("/boxing")
	public void registerBoxingStyleInfo(){
		System.out.println();
	}

}