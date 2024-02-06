package com.example.sparringday.config;

import com.example.sparringday.util.code.ApiExceptionCode;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{

    private final ApiExceptionCode errorCode;

    public CommonException(ApiExceptionCode errorCode){
        super(errorCode.msg);
        this.errorCode = errorCode;
    }

    public CommonException(String message, ApiExceptionCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
