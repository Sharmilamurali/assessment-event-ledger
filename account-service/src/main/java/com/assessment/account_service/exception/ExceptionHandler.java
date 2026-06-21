package com.assessment.account_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(AccountNotFoundException.class)
public ResponseEntity<Object> handleAccountNotFound(
AccountNotFoundException ex){

return ResponseEntity
.status(HttpStatus.NOT_FOUND)
.body(
new ErrorResponse(
Instant.now(),
404,
ex.getMessage()
)
);

}

@ExceptionHandler(Exception.class)
public ResponseEntity<Object> handleGeneric(
Exception ex){

return ResponseEntity
.status(HttpStatus.INTERNAL_SERVER_ERROR)
.body(
new ErrorResponse(
Instant.now(),
500,
"Internal server error"
)
);

}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Object> handleValidation(
MethodArgumentNotValidException ex){

String message =
ex.getBindingResult()
.getFieldErrors()
.get(0)
.getDefaultMessage();

return ResponseEntity
.status(HttpStatus.BAD_REQUEST)
.body(
new ErrorResponse(
Instant.now(),
400,
message
)
);

}

record ErrorResponse(
Instant timestamp,
int status,
String message
){}

}