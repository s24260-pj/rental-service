package com.example.demo.movie.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.ConnectException;

@RestControllerAdvice
public class MovieAdvice {
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFoundException(HttpClientErrorException.NotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleBadRequestException(HttpClientErrorException.BadRequest exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> handleBadGatewayException(HttpServerErrorException.InternalServerError exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<String> handleGatewayTimeoutException(ConnectException exception) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body(exception.getLocalizedMessage());
    }
}
