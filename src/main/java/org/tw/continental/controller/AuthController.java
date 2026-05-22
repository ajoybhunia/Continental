package org.tw.continental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tw.continental.controller.request.UserAuthRequest;
import org.tw.continental.exception.InvalidCredentialsException;
import org.tw.continental.exception.UserAlreadyExistsException;
import org.tw.continental.model.User;
import org.tw.continental.service.JWTService;
import org.tw.continental.service.UserService;

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
