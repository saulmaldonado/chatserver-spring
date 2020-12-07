package com.saulmaldonado.chatserver.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
  public UsernameAlreadyExistsException(String name) {
    super(String.format("Username %s already exists.", name));
  }
}
