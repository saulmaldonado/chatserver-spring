package com.saulmaldonado.chatserver.repositories;

import com.saulmaldonado.chatserver.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepository {

  private final Map<UUID, User> users = new HashMap<>();

  @Override
  public Optional<User> create(String name) {
    User newUser = new User(name);
    users.put(newUser.getUserId(), newUser);
    return Optional.of(newUser);
  }

  @Override
  public Optional<User> update(UUID id, String name) {
    User newUser = new User(id, name);
    users.put(id, newUser);

    return Optional.of(newUser);
  }

  @Override
  public Optional<User> delete(UUID id) {
    return Optional.ofNullable(users.remove(id));
  }

  @Override
  public Optional<User> findById(UUID id) {
    return Optional.ofNullable(users.get(id));
  }

  @Override
  public Optional<User> findByName(String name) {
    return users
            .values()
            .stream()
            .filter(user -> user.getName().equals(name))
            .findAny();
  }
}
