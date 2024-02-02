package com.example.sparringday.config;

import com.example.sparringday.dto.ApiExceptionResp;
import com.example.sparringday.util.code.ApiExceptionCode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler({CommonException.class})
    public ResponseEntity<ApiExceptionResp> exceptionHandler(final CommonException e) {
        log.error("[ApiExceptionAdvice.exceptionHandler] CommonException : errCd=" + e.getErrorCode().getCode() + ", errMsg=" + e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ApiExceptionResp.builder()
                        .errCd(e.getErrorCode().getCode())
                        .errMsg(e.getMessage())
                        .build());
    }
    @ExceptionHandler({ClassCastException.class})
    public ResponseEntity<ApiExceptionResp> exceptionHandler(final ClassCastException e) {
        log.error("[ApiExceptionAdvice.exceptionHandler] ClassCastException : errMsg=" + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiExceptionResp.builder()
                .errCd(ApiExceptionCode.NO_LOGIN_ERROR.getCode())
                .errMsg(ApiExceptionCode.NO_LOGIN_ERROR.getMsg())
                .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiExceptionResp> exceptionHandler(final MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        List<String> errorFieldList = result.getFieldErrors().stream().map(FieldError::getField).toList();
        log.error("[ApiExceptionAdvice.exceptionHandler] Method argument validation fail" + errorFieldList);

        return ResponseEntity.status(400).body(ApiExceptionResp.builder()
                .errCd(ApiExceptionCode.REQUEST_VALIDATION_EXCEPTION.getCode())
                .errMsg(ApiExceptionCode.REQUEST_VALIDATION_EXCEPTION.getMsg() + errorFieldList)
                .build());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ApiExceptionResp> exceptionHandler(final HttpMessageNotReadableException e) {
        log.error("[ApiExceptionAdvice.exceptionHandler] Bad Request Exception. errMsg=",e);

        return ResponseEntity.status(400).body(ApiExceptionResp.builder()
                .errCd(ApiExceptionCode.BAD_REQUEST_EXCEPTION.getCode())
                .errMsg(ApiExceptionCode.BAD_REQUEST_EXCEPTION.getMsg())
                .build());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiExceptionResp> exceptionHandler(final Exception e) {
        log.error("[ApiExceptionAdvice.exceptionHandler] Internal Exception. errMsg=", e);
        return ResponseEntity.status(400).body(ApiExceptionResp.builder()
                .errCd(ApiExceptionCode.INTERNAL_SERVER_EXCEPTION.getCode())
                .errMsg(ApiExceptionCode.INTERNAL_SERVER_EXCEPTION.getMsg())
                .build());
    }
}