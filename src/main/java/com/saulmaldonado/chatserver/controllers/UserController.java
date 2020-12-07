package com.saulmaldonado.chatserver.controllers;

import com.saulmaldonado.chatserver.exceptions.UserNotFoundException;
import com.saulmaldonado.chatserver.models.CreateUserForm;
import com.saulmaldonado.chatserver.models.User;
import com.saulmaldonado.chatserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    return userService.getUser(id);
  }

  @PostMapping("/create")
  public User createUser(@RequestBody CreateUserForm user) {
    return userService.createUser(user.name);
  }

  @DeleteMapping("/delete/{id}")
  public String createUser(@PathVariable UUID id) {
    boolean result = userService.deleteUser(id);
    if (result) {
      return String.format("User %s has been successfully deleted.", id);
    } else {
      throw new UserNotFoundException(id);
    }
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String userNotFound(UserNotFoundException ex) {
    return ex.getMessage();
  }
}
