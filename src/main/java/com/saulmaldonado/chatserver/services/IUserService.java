package com.saulmaldonado.chatserver.services;

import com.saulmaldonado.chatserver.models.User;

import java.util.UUID;

public interface IUserService {
  public User getUser(UUID id);

  public User createUser(String name);

  public boolean deleteUser(UUID id);

  public User editUser(UUID id, String Name);
}
