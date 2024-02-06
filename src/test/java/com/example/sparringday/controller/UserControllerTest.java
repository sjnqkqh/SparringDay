package com.example.sparringday.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.sparringday.config.TestContainerSettingConfig;
import com.example.sparringday.entity.User;
import com.example.sparringday.repository.UserRepository;
import com.example.sparringday.util.code.ApiExceptionCode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Import(TestContainerSettingConfig.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;

	@AfterEach
	@Transactional
	public void deleteAllUserData() {
		List<User> userList = userRepository.findAll();
		userList.forEach(user -> user.delete(LocalDateTime.now()));
	}

	@Test
	public void signUpTest_성공() throws Exception {
		// Given
		Map<String, Object> reqDto = Map.of("loginId", "UserID", "password", "Password");

		// When
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reqDto)))
			.andExpect(status().isOk())
			.andReturn();

		// Then
		String response = mvcResult.getResponse().getContentAsString();
		assertThat(response.contains("access_token")).isTrue();
		assertThat(response.contains("refresh_token")).isTrue();
	}

	@Test
	public void signUpTest_비밀번호_미포함_실패() throws Exception {
		// Given
		Map<String, Object> reqDto = Map.of("loginId", "UserID");

		// When Then
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reqDto)))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errCd").value(ApiExceptionCode.REQUEST_VALIDATION_EXCEPTION.code))
			.andReturn();

	}

	@Test
	public void signUpTest_Login_ID_미포함_실패() throws Exception {
		// Given
		Map<String, Object> reqDto = Map.of("password", "Password");

		// When Then
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reqDto)))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errCd").value(ApiExceptionCode.REQUEST_VALIDATION_EXCEPTION.code))
			.andReturn();
	}

	@Test
	public void signUpTest_Login_ID_중복_실패() throws Exception {
		// Given
		userRepository.save(User.builder().loginId("loginId").encryptedPassword("encPassword").build());
		userRepository.flush();

		Map<String, Object> reqDto = Map.of("loginId", "UserID", "password", "Password");

		// When Then
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reqDto)))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errCd").value(ApiExceptionCode.DUPLICATE_LOGIN_ID_EXIST_ERROR.code))
			.andReturn();
	}
}