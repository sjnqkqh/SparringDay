package com.example.sparringday.dto;

import lombok.Builder;

@Builder
public record ApiExceptionResp(String errCd, String errMsg) {

}