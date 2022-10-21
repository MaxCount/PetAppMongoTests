package com.petapp.persistance;

import com.petapp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends MongoRepository<User,String> {
    Optional<User> findByToken(String token);
}
