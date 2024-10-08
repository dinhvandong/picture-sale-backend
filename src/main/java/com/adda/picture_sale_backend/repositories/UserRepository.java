package com.adda.picture_sale_backend.repositories;

import com.adda.picture_sale_backend.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    // Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    boolean existsByEmailOrPhone(String email, String phone);



}
