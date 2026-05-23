package org.tw.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tw.userservice.controller.request.UserAuthRequest;
import org.tw.userservice.exception.InvalidCredentialsException;
import org.tw.userservice.exception.UserAlreadyExistsException;
import org.tw.userservice.model.User;
import org.tw.userservice.service.JWTService;
import org.tw.userservice.service.UserService;

@RestController
@RequestMapping("/api/users")
public class AuthController  {
  private final UserService userService;
  private final JWTService jwtService;

  public AuthController(UserService userService, JWTService jwtService) {
    this.userService = userService;
    this.jwtService = jwtService;
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
    String jwt = jwtService.generateToken(userService.getUserByName(request.username()));
    return ResponseEntity.ok().body(jwt);
  }
}
