package com.example.sparringday.controller;

import static com.example.sparringday.util.code.ApiExceptionCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.sparringday.config.TestContainerSettingConfig;
import com.example.sparringday.entity.User;
import com.example.sparringday.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Import(TestContainerSettingConfig.class)
@Transactional
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reqDto)))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errCd").value(REQUEST_VALIDATION_EXCEPTION.code));

	}

	@Test
	public void signUpTest_Login_ID_미포함_실패() throws Exception {
		// Given
		Map<String, Object> reqDto = Map.of("password", "Password");

		// When Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reqDto)))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errCd").value(REQUEST_VALIDATION_EXCEPTION.code));
	}

	@Test
	public void signUpTest_Login_ID_중복_실패() throws Exception {
		// Given
		userRepository.save(User.builder().loginId("loginId").encryptedPassword("encPassword").build());
		userRepository.flush();

		Map<String, Object> reqDto = Map.of("loginId", "loginId", "password", "Password");

		// When Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reqDto)))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errCd").value(DUPLICATE_LOGIN_ID_EXIST_ERROR.code));
	}

	@Test
	public void logInTest_Success() throws Exception {
		// Given
		Map<String, Object> reqDto = Map.of("loginId", "UserID", "password", "Password");
		userRepository.save(
			User.builder().loginId("UserID").encryptedPassword(passwordEncoder.encode("Password")).build());
		userRepository.flush();

		// When
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
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
	public void logInTest_MissingPassword_Failure() throws Exception {
		// Given
		Map<String, Object> reqDto = Map.of("loginId", "UserID");

		// When Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reqDto)))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errCd").value(REQUEST_VALIDATION_EXCEPTION.code));
	}

	@Test
	public void logInTest_MissingLoginID_Failure() throws Exception {
		// Given
		Map<String, Object> reqDto = Map.of("password", "Password");

		// When Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reqDto)))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errCd").value(REQUEST_VALIDATION_EXCEPTION.code));
	}

	@Test
	public void logInTest_invalidCredentials_Failure() throws Exception {
		// Given
		Map<String, Object> reqDto = Map.of("loginId", "UserID", "password", "WrongPassword");
		userRepository.save(User.builder().loginId("UserID").encryptedPassword("Password").build());
		userRepository.flush();

		// When Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reqDto)))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.errCd").value(INVALID_LOGIN_REQUEST_ERROR.code));
	}

}