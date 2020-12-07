package com.saulmaldonado.chatserver.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
  private final UUID id;

  public UserNotFoundException(UUID id) {
    super(String.format("User %s not found.", id));
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
}
