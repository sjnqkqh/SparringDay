package com.example.sparringday.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sparringday.entity.User;
import com.example.sparringday.repository.TokenRepository;
import com.example.sparringday.service.UserService;
import com.example.sparringday.util.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserService userService;
	private final TokenRepository tokenRepository;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain) throws ServletException, IOException {
		if (request.getServletPath().contains("/api/v1/auth")) {
			filterChain.doFilter(request, response);
			return;
		}
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final Long userId;

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		userId = jwtService.extractUserId(jwt);
		if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = userService.findUserById(userId);
			boolean isTokenValid = tokenRepository.findByToken(jwt)
				.map(t -> !t.isRevoked() && !t.isExpired())
				.orElse(false);
			if (jwtService.isTokenValid(jwt, user) && isTokenValid) {
				// UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				// 	user,
				// 	null,
				// 	userDetails.getAuthorities()
				// );
			}
		}


	}
}
