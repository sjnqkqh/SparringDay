package com.example.sparringday.config;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SpringSecurityConfig {

	private final JwtAuthenticationFilter authenticationFilter;

	private static final String[] WHITE_LIST_URL = {"/api/v1/user/**",};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
			.cors(AbstractHttpConfigurer::disable)

			.authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URL).permitAll().anyRequest().authenticated())
			.formLogin(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
			.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.logout(Customizer.withDefaults())
			// .authenticationProvider()
			.build();

	}

}
