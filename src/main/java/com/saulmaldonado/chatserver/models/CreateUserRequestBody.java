package com.saulmaldonado.chatserver.models;

public class CreateUserRequestBody {
  public String name;

  public CreateUserRequestBody(String name) {
    this.name = name;
  }
}
