package com.pm.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<Map<String,String>>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        //create a hashmap --->that is what we will return
        Map<String,String>errors=new HashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(error->errors.put(error.getField(),error.getDefaultMessage()));

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ResponseEntity<Map<String,String>>
    handleEmailAlreadyExistsException(EmailAlreadyExistsException exception){
        log.info("email Already exist {}%s".formatted(exception.getMessage()));
         Map<String,String>error=new HashMap<>();
         error.put("email",exception.getMessage());
         return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    private ResponseEntity<Map<String,String>>
    handlePatientNotFoundException(PatientNotFoundException exception){
        log.warn("patient not found {}", exception.getMessage());
        Map<String,String>error=new HashMap<>();
        error.put("patient Not Found",exception.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
