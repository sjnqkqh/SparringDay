package com.example.sparringday.config;

import java.io.IOException;
import java.text.Annotation;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sparringday.util.RequiredUser;
import com.example.sparringday.util.code.ApiExceptionCode;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckRequiredUserFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain) throws ServletException, IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new CommonException(ApiExceptionCode.NO_LOGIN_ERROR);
		}
		Object principal = authentication.getPrincipal();
		RequiredUser requiredUser = AnnotationUtils.findAnnotation(RequiredUser.class, )


	}
}
