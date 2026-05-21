package org.tw.continental.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tw.continental.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
  User findUserById(Integer id);

  Optional<Integer> findTopByOrderByIdDesc();
}
