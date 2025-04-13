package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleZooException(ZooException exc) {
        log.error("ZooException occurred: {}", exc.getMessage());
        ZooErrorResponse response = new ZooErrorResponse(exc.getMessage(), exc.getHttpStatus().value(), System.currentTimeMillis());
        return new ResponseEntity<>(response, exc.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleGenericException(Exception exc) {
        log.error("Unexpected exception occurred: {}", exc.getMessage(), exc);
        ZooErrorResponse response = new ZooErrorResponse("Beklenmedik bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}