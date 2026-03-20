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

    public UserResponse getById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with the id : " + id));
        return userMapper.toResponse(user);
    }

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

    public UserResponse updatePassword(String id, UserPasswordRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with the id : " + id));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toResponse(userRepository.save(user));
    }

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

    public List<ThemeResponse> getSubscriptions() {
        User currentUser = authUtils.getCurrentUser();
        return currentUser.getSubscriptions()
                .stream()
                .map(themeMapper::toResponse)
                .collect(Collectors.toList());
    }
}