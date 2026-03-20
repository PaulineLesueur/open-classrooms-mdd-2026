package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Utility component that provides access to the currently authenticated user.
 */
@Component
@RequiredArgsConstructor
public class AuthUtils {

    private final UserRepository userRepository;

    /**
     * Returns the {@link User} entity corresponding to the currently authenticated principal.
     * <p>
     * Retrieves the email from the {@link org.springframework.security.core.context.SecurityContext}
     * and looks up the matching user in the database.
     * </p>
     *
     * @return the authenticated user entity
     * @throws RuntimeException if no matching user is found in the database
     */
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmailOrUsername(email, email)
                .orElseThrow(() -> new RuntimeException("No user logged found"));
    }
}