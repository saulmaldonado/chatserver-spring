package com.saulmaldonado.chatserver.unit.controllers;

import com.saulmaldonado.chatserver.exceptions.UserNotFoundException;
import com.saulmaldonado.chatserver.exceptions.UsernameAlreadyExistsException;
import com.saulmaldonado.chatserver.models.CreateUserRequestBody;
import com.saulmaldonado.chatserver.models.EditUserRequestBody;
import com.saulmaldonado.chatserver.models.User;
import com.saulmaldonado.chatserver.repositories.UserRepository;
import com.saulmaldonado.chatserver.controllers.UserController;
import com.saulmaldonado.chatserver.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserControllerTests {
  UserRepository userRepository;
  UserService userService;
  UserController userController;
  String testUserName = "test";

  @BeforeEach
  void init() {
    userRepository = new UserRepository();
    userService = new UserService(userRepository);
    userController = new UserController(userService);
  }

  private CreateUserRequestBody newCreateUserRequestBody() {
    return new CreateUserRequestBody(testUserName);
  }

  private User createTestUser() throws Exception {
    Optional<User> user = userRepository.create(testUserName);

    if (user.isPresent()) {
      return user.get();
    }

    throw new Exception("userRepository.create not returning test user");
  }

  @Test
  public void shouldCreateUser() {
    CreateUserRequestBody newUserRequest = newCreateUserRequestBody();

    User resultUser = userController.createUser(newUserRequest);

    assertThat(resultUser).isNotNull();
    assertThat(resultUser.getName()).isEqualTo(testUserName);
  }

  @Test
  public void createUserShouldThrowIfUsernameAlreadyExists() {
    userRepository.create(testUserName); // Creates existing user
    CreateUserRequestBody newUserRequest = newCreateUserRequestBody();

    assertThatThrownBy(() -> {
      userController.createUser(newUserRequest);
    }).isInstanceOf(UsernameAlreadyExistsException.class);
  }

  @Test
  public void shouldGetUser() throws Exception {
    User testUser = createTestUser(); // Creates existing user

    assertThat(userController.getUser(testUser.getUserId())).isEqualTo(testUser);
  }

  @Test
  public void shouldThrowIfUserIsNonExistentWhenGettingUser() throws Exception {
    UUID randomUserId = UUID.randomUUID();

    assertThatThrownBy(() -> userController.getUser(randomUserId))
            .isInstanceOf(UserNotFoundException.class);
  }

  @Test
  public void deleteUser() throws Exception {
    User testUser = createTestUser(); // Creates existing user

    String successMessage = userController.deleteUser(testUser.getUserId());

    assertThat(successMessage)
            .isEqualTo(String.format("User %s has been successfully deleted.",
                    testUser.getUserId()));
  }

  @Test
  public void shouldThrowIfUserIsNonExistentWhenDeletingUser() {
    UUID randomUserId = UUID.randomUUID();

    assertThatThrownBy(() -> userController.deleteUser(randomUserId))
            .isInstanceOf(UserNotFoundException.class);
  }

  @Test
  public void shouldEditUser() throws Exception {
    User testUser = createTestUser(); // Creates existing user
    String newUsername = "test1";
    EditUserRequestBody editUserRequest = new EditUserRequestBody(newUsername);

    User editedUser = userController.editUser(testUser.getUserId(), editUserRequest);

    assertThat(editedUser.getName()).isEqualTo(newUsername);
  }

  @Test
  public void shouldThrowIfUserIsNonExistentWhenEditingUser() {
    UUID randomUserId = UUID.randomUUID();
    String newUsername = "test1";
    EditUserRequestBody editUserRequest = new EditUserRequestBody(newUsername);

    assertThatThrownBy(() -> userController.editUser(randomUserId, editUserRequest))
            .isInstanceOf(UserNotFoundException.class);
  }
}
