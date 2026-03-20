package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for {@link User} entities.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD operations,
 * and declares additional query methods for authentication and uniqueness checks.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Checks whether a user with the given username already exists.
     *
     * @param username the username to check
     * @return {@code true} if a user with this username exists, {@code false} otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Checks whether a user with the given email already exists.
     *
     * @param email the email address to check
     * @return {@code true} if a user with this email exists, {@code false} otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Finds a user by email or username, used during authentication.
     *
     * @param email    the email address to search for
     * @param username the username to search for
     * @return an {@link Optional} containing the matching user, or empty if not found
     */
    Optional<User> findByEmailOrUsername(String email, String username);
}
