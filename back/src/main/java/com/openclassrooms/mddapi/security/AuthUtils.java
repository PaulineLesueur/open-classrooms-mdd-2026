package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtils {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmailOrUsername(email, email)
                .orElseThrow(() -> new RuntimeException("No user logged found"));
    }
}