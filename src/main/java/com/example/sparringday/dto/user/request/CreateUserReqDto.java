package com.example.sparringday.dto.user.request;

import com.example.sparringday.util.code.UserType;

public record CreateUserReqDto(String loginId, String password, UserType userType) {
}
