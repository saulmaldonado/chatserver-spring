package com.saulmaldonado.chatserver.services;

import com.saulmaldonado.chatserver.exceptions.UserNotFoundException;
import com.saulmaldonado.chatserver.models.User;
import com.saulmaldonado.chatserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserService implements IUserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getUser(UUID id) {
    return userRepository.find(id);
  }

  @Override
  public User createUser(String name) {
    return userRepository.create(new User(name));
  }

  @Override
  public boolean deleteUser(UUID id) {
    User user = userRepository.delete(id);
    return Objects.nonNull(user);
  }

  @Override
  public User editUser(UUID id, String name) {
    return userRepository.update(id, new User(id, name));
  }
}
