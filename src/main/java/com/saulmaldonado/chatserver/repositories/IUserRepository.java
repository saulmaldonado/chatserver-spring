package com.saulmaldonado.chatserver.repositories;

import com.saulmaldonado.chatserver.models.User;

import java.util.UUID;

public interface IUserRepository {
  public User create(User user);

  public User update(UUID id, User user);

  public boolean delete(UUID id);

  public User find(UUID id);
}
