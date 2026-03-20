package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.requests.LoginRequest;
import com.openclassrooms.mddapi.dto.requests.RegisterRequest;
import com.openclassrooms.mddapi.dto.requests.UserPasswordRequest;
import com.openclassrooms.mddapi.dto.requests.UserRequest;
import com.openclassrooms.mddapi.dto.responses.AuthResponse;
import com.openclassrooms.mddapi.dto.responses.ThemeResponse;
import com.openclassrooms.mddapi.dto.responses.UserResponse;
import com.openclassrooms.mddapi.entities.Theme;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.mappers.ThemeMapper;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.security.AuthUtils;
import com.openclassrooms.mddapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class handling business logic for user management and authentication.
 * <p>
 * Covers registration, login, profile updates, password changes, and theme subscriptions.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final AuthUtils authUtils;
    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    /**
     * Retrieves a user by their ID.
     *
     * @param id the UUID of the user
     * @return the user as a response DTO
     * @throws RuntimeException if no user is found with the given ID
     */
    public UserResponse getById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with the id : " + id));
        return userMapper.toResponse(user);
    }

    /**
     * Updates a user's profile information (username and email).
     *
     * @param id      the UUID of the user to update
     * @param request the DTO containing the new username and email
     * @return the updated user as a response DTO
     * @throws RuntimeException if the user is not found, or if the new username/email is already in use
     */
    public UserResponse update(String id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with the id : " + id));
        if (!user.getUsername().equals(request.getUsername())
                && userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already in use");
        }
        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        return userMapper.toResponse(userRepository.save(user));
    }

    /**
     * Updates a user's password.
     *
     * @param id      the UUID of the user
     * @param request the DTO containing the new plain-text password
     * @return the updated user as a response DTO
     * @throws RuntimeException if no user is found with the given ID
     */
    public UserResponse updatePassword(String id, UserPasswordRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with the id : " + id));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toResponse(userRepository.save(user));
    }

    /**
     * Registers a new user and returns a JWT token.
     *
     * @param request the DTO containing the registration data
     * @return an auth response with the JWT token and user information
     * @throws RuntimeException if the email or username is already in use
     */
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        String token = jwtTokenProvider.generateToken(user.getId());
        return new AuthResponse(token, user.getId(), user.getUsername(), user.getEmail());
    }

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param request the DTO containing the identifier (email or username) and password
     * @return an auth response with the JWT token and user information
     * @throws RuntimeException if the credentials are invalid or the user is not found
     */
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getIdentifier(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmailOrUsername(request.getIdentifier(), request.getIdentifier())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtTokenProvider.generateToken(user.getId());
        return new AuthResponse(token, user.getId(), user.getUsername(), user.getEmail());
    }

    /**
     * Subscribes the currently authenticated user to a theme.
     *
     * @param themeId the ID of the theme to subscribe to
     * @throws RuntimeException if the theme is not found or the user is already subscribed
     */
    public void subscribe(Integer themeId) {
        User currentUser = authUtils.getCurrentUser();
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found with id : " + themeId));

        if (currentUser.getSubscriptions().contains(theme)) {
            throw new RuntimeException("You are already subscribed to this theme");
        }

        currentUser.getSubscriptions().add(theme);
        userRepository.save(currentUser);
    }

    /**
     * Unsubscribes the currently authenticated user from a theme.
     *
     * @param themeId the ID of the theme to unsubscribe from
     * @throws RuntimeException if the theme is not found or the user is not subscribed
     */
    public void unsubscribe(Integer themeId) {
        User currentUser = authUtils.getCurrentUser();
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found with id : " + themeId));

        if (!currentUser.getSubscriptions().contains(theme)) {
            throw new RuntimeException("You are not subscribed to this theme");
        }

        currentUser.getSubscriptions().remove(theme);
        userRepository.save(currentUser);
    }

    /**
     * Retrieves the list of themes the currently authenticated user is subscribed to.
     *
     * @return a list of subscribed themes as response DTOs
     */
    public List<ThemeResponse> getSubscriptions() {
        User currentUser = authUtils.getCurrentUser();
        return currentUser.getSubscriptions()
                .stream()
                .map(themeMapper::toResponse)
                .collect(Collectors.toList());
    }
}