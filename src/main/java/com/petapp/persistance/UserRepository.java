package com.petapp.persistance;

import com.petapp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

//    @Query("{'username' : ?0}")
//    User findByUsername(String username);
}
