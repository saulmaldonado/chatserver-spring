package com.saulmaldonado.chatserver.repositories;

import com.saulmaldonado.chatserver.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepository {

  private final Map<UUID, User> users = new HashMap<>();

  @Override
  public User create(User user) {
    users.put(user.getUserId(), user);
    return user;
  }

  @Override
  public User update(UUID id, User user) {
    users.put(id, user);
    return user;
  }

  @Override
  public boolean delete(UUID id) {
    users.remove(id);
    return true;
  }

  @Override
  public User find(UUID id) {
    return users.get(id);
  }
}
