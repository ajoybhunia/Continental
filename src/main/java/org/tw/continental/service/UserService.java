package org.tw.continental.service;

import org.springframework.stereotype.Service;
import org.tw.continental.exception.InvalidCredentialsException;
import org.tw.continental.exception.UserAlreadyExistsException;
import org.tw.continental.model.User;
import org.tw.continental.projection.UserId;
import org.tw.continental.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private Integer currentID;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.currentID = userRepository.findTopByOrderByIdDesc().map(UserId::getId).orElse(0);
  }

  public User saveUser(String username, String password) throws UserAlreadyExistsException {
    final Integer id = ++currentID;
    if (userRepository.findUserByUsername(username).isPresent()) {
      throw new UserAlreadyExistsException(username);
    }
    return userRepository.save(new User(id, username, password));
  }

  public void validateUser(String username, String password) throws InvalidCredentialsException {
    Optional<User> user = userRepository.findByUsernameAndPassword(username, password);

    if (user.isEmpty()) {
      throw new InvalidCredentialsException();
    }
  }
}
