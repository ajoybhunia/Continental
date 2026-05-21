package org.tw.continental.service;

import org.springframework.stereotype.Service;
import org.tw.continental.model.User;
import org.tw.continental.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private Integer currentID;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.currentID = userRepository.findTopByOrderByIdDesc().orElse(0);
  }

  public User saveUser(String username, String password) {
    final Integer id = ++currentID;

    return new User(id, username, password);
  }

}
