package com.tweteroo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler({ UserNotFoundException.class })
  public ResponseEntity<String> handleUserNotFound(UserNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({ UnprocessableUserIdException.class })
  public ResponseEntity<String> handleUnprocessableUserId(UnprocessableUserIdException exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }

  @ExceptionHandler({ ConflictUsernameException.class })
  public ResponseEntity<String> handleConflictUsername(ConflictUsernameException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }
}
