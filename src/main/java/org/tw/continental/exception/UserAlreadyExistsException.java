package org.tw.continental.exception;

public class UserAlreadyExistsException extends Throwable {
  public UserAlreadyExistsException(String username) {
    super("%s already exists".formatted(username));
  }
}
