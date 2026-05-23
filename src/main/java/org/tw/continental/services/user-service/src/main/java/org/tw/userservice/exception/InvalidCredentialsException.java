package org.tw.userservice.exception;

public class InvalidCredentialsException extends Throwable {
  public InvalidCredentialsException() {
    super("Invalid Credentials");
  }
}
