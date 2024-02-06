package com.example.sparringday.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.sparringday.TestSparringDayApplication;
import com.example.sparringday.repository.UserRepository;

@SpringBootTest
@Import(TestSparringDayApplication.class)
public class SetupDatabaseTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void createBoxingInfo_ValidInput_ValidOutput()  {
		System.out.println("userRepository.findAll() = " + userRepository.findAll());
	}
}