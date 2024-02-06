package com.example.sparringday;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.sparringday.config.TestContainerSettingConfig;
import com.example.sparringday.repository.UserRepository;

@SpringBootTest
@Import(TestContainerSettingConfig.class)
public class SetupDatabaseTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void createBoxingInfo_ValidInput_ValidOutput()  {
		System.out.println("userRepository.findAll() = " + userRepository.findAll());
	}
}