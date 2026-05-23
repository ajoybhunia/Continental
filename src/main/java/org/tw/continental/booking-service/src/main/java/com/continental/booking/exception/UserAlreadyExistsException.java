package com.continental.booking.exception;

public class UserAlreadyExistsException extends Throwable {
  public UserAlreadyExistsException(String username) {
    super("%s already exists".formatted(username));
  }
}
