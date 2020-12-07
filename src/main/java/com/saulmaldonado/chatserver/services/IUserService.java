package com.saulmaldonado.chatserver.services;

import com.saulmaldonado.chatserver.models.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {
  public Optional<User> getUser(UUID id);

  public Optional<User> createUser(String name);

  public boolean deleteUser(UUID id);

  public Optional<User> editUser(UUID id, String Name);
}
