package org.tw.userservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tw.userservice.exception.InvalidCredentialsException;
import org.tw.userservice.exception.UserAlreadyExistsException;
import org.tw.userservice.model.User;
import org.tw.userservice.projection.UserId;
import org.tw.userservice.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

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

  public User getUserByName(String username) {
    return userRepository.findUserByUsername(username).orElse(null);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findUserByUsername(username).orElse(null);
    if(user == null) throw new UsernameNotFoundException("Username %s not found".formatted(username));

    return org.springframework.security.core.userdetails.User.builder().username(username).roles("USER").build();
  }
}
