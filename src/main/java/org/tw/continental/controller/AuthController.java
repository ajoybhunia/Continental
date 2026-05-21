package org.tw.continental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tw.continental.controller.request.UserRegistrationRequest;
import org.tw.continental.models.User;
import org.tw.continental.service.UserService;

@RestController
@RequestMapping("/api/users")
public class AuthController  {
  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register (@RequestBody UserRegistrationRequest request){
    User user = userService.saveUser(request.username(), request.password());

    return ResponseEntity.ok(user);
  }
}
