package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.requests.UserPasswordRequest;
import com.openclassrooms.mddapi.dto.requests.UserRequest;
import com.openclassrooms.mddapi.dto.responses.UserResponse;
import com.openclassrooms.mddapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST controller exposing endpoints for user profile management.
 * Base path: {@code /api/users}
 */
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Retrieves a user's profile by their ID.
     *
     * @param id the UUID of the user
     * @return HTTP 200 with the user's profile
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    /**
     * Updates the authenticated user's profile (username and email).
     *
     * @param request the request body containing the new username and email
     * @return HTTP 200 with the updated user profile
     */
    @PutMapping("/me")
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(userService.update(request));
    }

    /**
     * Updates the authenticated user's password.
     *
     * @param request the request body containing the new password
     * @return HTTP 200 with the updated user profile
     */
    @PutMapping("/me/password")
    public ResponseEntity<UserResponse> updatePassword(@Valid @RequestBody UserPasswordRequest request) {
        return ResponseEntity.ok(userService.updatePassword(request));
    }
}
