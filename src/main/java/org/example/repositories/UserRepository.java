package org.example.repositories;


import org.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByUsername (String username);

    // JPA will implement the actual SQL logic to be able to query
    // the database and search the Users table for the User with a
    // username that equals the string we gave it
}
