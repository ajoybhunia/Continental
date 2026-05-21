package org.tw.continental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tw.continental.controller.request.UserAuthRequest;
import org.tw.continental.exception.InvalidCredentialsException;
import org.tw.continental.exception.UserAlreadyExistsException;
import org.tw.continental.model.User;
import org.tw.continental.service.UserService;

@RestController
@RequestMapping("/api/users")
public class AuthController  {
  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register (@RequestBody UserAuthRequest request){
    User user;
    try {
      user = userService.saveUser(request.username(), request.password());
    } catch (UserAlreadyExistsException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

    return ResponseEntity.ok(user);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserAuthRequest request){
    try {
      userService.validateUser(request.username(), request.password());
    } catch (InvalidCredentialsException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok().build();
  }
}
