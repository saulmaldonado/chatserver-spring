package com.saulmaldonado.chatserver.models;

import java.util.UUID;

public interface IUser {
  public UUID getUserId();
  public String getName();
  public String setName(String name);
}
