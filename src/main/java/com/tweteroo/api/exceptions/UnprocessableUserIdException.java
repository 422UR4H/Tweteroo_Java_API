package com.tweteroo.api.exceptions;

public class UnprocessableUserIdException extends RuntimeException {
  public UnprocessableUserIdException(String message) {
    super(message);
  }

  public UnprocessableUserIdException() {
    super("Unprocessable userId");
  }
}
