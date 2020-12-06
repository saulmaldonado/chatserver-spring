package com.saulmaldonado.chatserver.controllers;

import com.saulmaldonado.chatserver.models.CreateUserForm;
import com.saulmaldonado.chatserver.models.User;
import com.saulmaldonado.chatserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
