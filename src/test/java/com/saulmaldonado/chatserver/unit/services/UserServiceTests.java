package com.saulmaldonado.chatserver.unit.services;

import com.saulmaldonado.chatserver.models.User;
import com.saulmaldonado.chatserver.repositories.UserRepository;
import com.saulmaldonado.chatserver.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

  UserRepository userRepository;
  UserService userService;
  String testUserName = "test";

  @BeforeEach
  void init() {
    userRepository = new UserRepository();
    userService = new UserService(userRepository);
  }

  private User createTestUser() throws Exception {
    Optional<User> user = userRepository.create(testUserName);

    if (user.isPresent()) {
      return user.get();
    }

    throw new Exception("userRepository.create not returning test user");
  }

  @Test
  public void shouldCreateNewUser() throws Exception {
    Optional<User> testUser = userService.createUser(testUserName);

    assertThat(testUser).isPresent();
    assertThat(testUser.get().getName()).isEqualTo(testUserName);
  }

  @Test
  public void shouldReturnEmptyIfUsernameAlreadyExists() throws Exception {
    createTestUser(); // Creates existing user

    Optional<User> testUser = userService.createUser(testUserName);
    assertThat(testUser).isEmpty();
  }

  @Test
  public void shouldReturnExistingUser() throws Exception {
    User testUser = createTestUser(); // Creates existing user

    Optional<User> existingUser = userService.getUser(testUser.getUserId());
    assertThat(existingUser).isPresent();
    assertThat(existingUser.get().getName()).isEqualTo(testUserName);
  }

  @Test
  public void shouldReturnEmptyIfUserIsNonExistent() {
    Optional<User> nonExistentUser = userService.getUser(UUID.randomUUID());

    assertThat(nonExistentUser).isEmpty();
  }

  @Test
  public void shouldDeleteUser() throws Exception {
    User testUser = createTestUser();
    UUID testUserId = testUser.getUserId();

    boolean result = userService.deleteUser(testUserId);
    Optional<User> foundUser = userService.getUser(testUserId);

    assertThat(result).isTrue();
    assertThat(foundUser).isEmpty();
  }

  @Test
  public void shouldReturnFalseIfUserToBeDeletedIsNotExistent() throws Exception {
    UUID randomUUID = UUID.randomUUID();

    boolean result = userService.deleteUser(randomUUID);

    assertThat(result).isFalse();
  }

  @Test
  public void shouldEditUser() throws Exception {
    User existingUser = createTestUser();
    String newUserName = "newtest";

    Optional<User> editedUser = userService.editUser(existingUser.getUserId(), newUserName);

    assertThat(editedUser).isPresent();

    assertThat(editedUser.get().getName()).isEqualTo(newUserName);
  }

}
