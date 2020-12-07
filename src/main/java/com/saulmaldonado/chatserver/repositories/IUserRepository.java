package com.saulmaldonado.chatserver.repositories;

import com.saulmaldonado.chatserver.models.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
  public Optional<User> create(String name);

  public Optional<User> update(UUID id, User user);

  public Optional<User> delete(UUID id);

  public Optional<User> findById(UUID id);

  public Optional<User> findByName(String name);
}
