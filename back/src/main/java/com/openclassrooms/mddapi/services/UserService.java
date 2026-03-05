package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.requests.UserRequest;
import com.openclassrooms.mddapi.dto.responses.UserResponse;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse getById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with the id : " + id));
        return userMapper.toResponse(user);
    }

    public UserResponse update(UUID id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with the id : " + id));
        if(!user.getUsername().equals(request.getUsername()) && userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already in use");
        }

        if(!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        return userMapper.toResponse(userRepository.save(user));
    }
}
