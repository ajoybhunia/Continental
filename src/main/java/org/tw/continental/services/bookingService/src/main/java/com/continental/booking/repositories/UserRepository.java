package com.continental.booking.repositories;

import com.continental.booking.modles.User;
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
