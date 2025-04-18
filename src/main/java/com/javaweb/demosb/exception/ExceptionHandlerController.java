package com.javaweb.demosb.exception;

import com.javaweb.demosb.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({RuntimeException.class, Exception.class}) //{} for many class
    @ResponseBody()
    protected ResponseEntity handleException(Exception e) {
        return ResponseEntity.badRequest().body(ResponseDTO.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody()
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    protected ResponseEntity handleBusinessException(Exception e) {
        return ResponseEntity.internalServerError().body(ResponseDTO.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody()
    @ResponseStatus(BAD_REQUEST)
    protected ResponseEntity handleAuthException(Exception e) {
        return ResponseEntity.badRequest().body(ResponseDTO.builder().message(e.getMessage()).build());
    }
}
