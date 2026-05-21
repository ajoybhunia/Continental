package org.tw.continental.repository;

import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tw.continental.model.User;
import org.tw.continental.model.User;
import org.tw.continental.projection.UserId;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
  User findUserById(Integer id);

  Optional<UserId> findTopByOrderByIdDesc();

  Optional<User> findByUsernameAndPassword(String username, String password);

  Optional<User> findUserByUsername(String username);
}
