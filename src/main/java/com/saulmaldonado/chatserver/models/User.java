package com.saulmaldonado.chatserver.models;

import java.util.UUID;

public class User implements IUser {
  private final UUID id;
  private String name;

  public User(String name) {
    id = UUID.randomUUID();
    this.name = name;
  }

  public User(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public UUID getUserId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String setName(String name) {
    this.name = name;
    return name;
  }
}
