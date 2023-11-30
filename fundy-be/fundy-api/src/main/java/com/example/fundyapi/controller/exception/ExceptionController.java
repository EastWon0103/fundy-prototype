package com.example.fundyapi.controller.exception;

import com.example.fundyapi.common.exception.DuplicateUserException;
import com.example.fundyapi.common.exception.NoProjectException;
import com.example.fundyapi.common.exception.NoUserException;
import com.example.fundyapi.common.response.GlobalExceptionResponse;
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
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final GlobalExceptionResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error("잘못되 RequestBody");
        return makeResponse(e.getBindingResult()
            .getFieldErrors()
            .stream().map((error)-> error.getDefaultMessage())
            .collect(Collectors.toList()));
    }

    @ExceptionHandler({NoUserException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public final GlobalExceptionResponse handleNoUserException(final NoUserException e) {
        return makeResponse(e.getMessage());
    }

    @ExceptionHandler({NoProjectException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final GlobalExceptionResponse handleNoProjectException(final NoProjectException e) {
        return makeResponse(e.getMessage());
    }

    @ExceptionHandler({DuplicateUserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final GlobalExceptionResponse handleDuplicateUserException(final DuplicateUserException e) {
        return makeResponse(e.getMessage());
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
