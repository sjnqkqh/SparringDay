package com.example.sparringday.dto.user.request;

import jakarta.validation.constraints.NotNull;

public record CreateUserReqDto(@NotNull String loginId, @NotNull String password) {
}
