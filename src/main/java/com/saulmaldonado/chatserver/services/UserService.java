package com.saulmaldonado.chatserver.services;

import com.saulmaldonado.chatserver.models.User;
import com.saulmaldonado.chatserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> getUser(UUID id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<User> createUser(String name) {
    if (userRepository.findByName(name).isEmpty()) {
      return userRepository.create(name);
    }
    return Optional.empty();
  }

  @Override
  public boolean deleteUser(UUID id) {
    Optional<User> user = userRepository.delete(id);
    return user.isPresent();
  }

  @Override
  public Optional<User> editUser(UUID id, String name) {
    if (userRepository.findById(id).isPresent()) {
      return userRepository.update(id, name);
    }
    return Optional.empty();
  }
}
