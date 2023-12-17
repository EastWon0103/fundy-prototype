package com.example.api.controller.exception;

import com.example.api.common.response.GlobalExceptionResponse;
import com.example.core.utils.exception.CoreApplicationException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Hidden
@Slf4j
public class ExceptionController {
    @ExceptionHandler({CoreApplicationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final GlobalExceptionResponse handleCoreApplicationException(final CoreApplicationException e) {
        return makeResponse(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final GlobalExceptionResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error("잘못되 RequestBody");
        return makeResponse(e.getBindingResult()
            .getFieldErrors()
            .stream().map((error)-> error.getDefaultMessage())
            .collect(Collectors.toList()));
    }

    private GlobalExceptionResponse<String> makeResponse(String message) {
        return GlobalExceptionResponse.<String>builder()
            .message(message)
            .build();
    }

    private GlobalExceptionResponse<List<String>> makeResponse(List<String> messages) {
        return GlobalExceptionResponse.<List<String>>builder()
            .message(messages)
            .build();
    }
}
