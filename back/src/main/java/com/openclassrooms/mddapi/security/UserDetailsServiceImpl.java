package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Implementation of Spring Security's {@link UserDetailsService}.
 * <p>
 * Provides two user-loading strategies:
 * <ul>
 *   <li>{@link #loadUserByUsername(String)} — used by {@code DaoAuthenticationProvider} during login,
 *       looks up by email or username.</li>
 *   <li>{@link #loadUserById(String)} — used by {@link JwtAuthenticationFilter} on every authenticated request,
 *       looks up by UUID to remain valid after an email change.</li>
 * </ul>
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Loads a user by their email or username. Used by Spring Security during login authentication.
     *
     * @param identifier the email address or username
     * @return a {@link UserDetails} object built from the matching user
     * @throws UsernameNotFoundException if no user is found with the given identifier
     */
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrUsername(identifier, identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found : " + identifier));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }

    /**
     * Loads a user by their UUID. Used by {@link JwtAuthenticationFilter} to authenticate
     * incoming requests without being affected by email or username changes.
     *
     * @param id the UUID of the user
     * @return a {@link UserDetails} object built from the matching user
     * @throws UsernameNotFoundException if no user is found with the given ID
     */
    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}