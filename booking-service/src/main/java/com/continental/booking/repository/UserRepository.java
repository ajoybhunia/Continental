package com.continental.booking.repository;

import com.continental.booking.model.User;
import com.continental.booking.projection.UserId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
  User findUserById(Integer id);

  Optional<UserId> findTopByOrderByIdDesc();

  Optional<User> findByUsernameAndPassword(String username, String password);

  Optional<User> findUserByUsername(String username);
}
