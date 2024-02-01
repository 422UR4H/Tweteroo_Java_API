package com.tweteroo.api.exceptions;

public class ConflictUsernameException extends RuntimeException {
  public ConflictUsernameException(String message) {
    super(message);
  }

  public ConflictUsernameException() {
    super("Username already exists");
  }
}
