package com.paras.linkedInProject.userService.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus httpStatus;
    public ApiError(){this.timeStamp = LocalDateTime.now();}

    public ApiError(String message, HttpStatus statusCode){
        this();
        this.error = message;
        this.httpStatus = statusCode;
    }
}
