package com.saulmaldonado.chatserver.controllers;

import com.saulmaldonado.chatserver.exceptions.UserNotFoundException;
import com.saulmaldonado.chatserver.exceptions.UsernameAlreadyExistsException;
import com.saulmaldonado.chatserver.models.CreateUserRequestBody;
import com.saulmaldonado.chatserver.models.EditUserRequestBody;
import com.saulmaldonado.chatserver.models.User;
import com.saulmaldonado.chatserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable UUID id) {
    Optional<User> user = userService.getUser(id);
    return user.orElseThrow(() -> new UserNotFoundException(id));
  }

  @PostMapping("/create")
  public User createUser(@RequestBody CreateUserRequestBody user) {
    return userService
            .createUser(user.name)
            .orElseThrow(() -> new UsernameAlreadyExistsException(user.name));
  }

  @DeleteMapping("/delete/{id}")
  public String deleteUser(@PathVariable UUID id) {
    boolean result = userService.deleteUser(id);
    if (result) {
      return String.format("User %s has been successfully deleted.", id);
    }
    throw new UserNotFoundException(id);
  }

  @PatchMapping("/edit/{id}")
  public User editUser(@PathVariable UUID id, @RequestBody EditUserRequestBody user) {
    Optional<User> editedUser = userService.editUser(id, user.name);
    return editedUser.orElseThrow(() -> new UserNotFoundException(id));
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String userNotFound(UserNotFoundException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler(UsernameAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public String UsernameAlreadyExists(UsernameAlreadyExistsException ex) {
    return ex.getMessage();
  }
}
