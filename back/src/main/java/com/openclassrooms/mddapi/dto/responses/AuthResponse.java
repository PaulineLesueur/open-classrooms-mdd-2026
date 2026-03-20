package com.openclassrooms.mddapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO returned after a successful login or registration.
 * Contains the JWT token and basic user information.
 */
@Getter
@Setter
public class AuthResponse {
    private String token;
    private String id;
    private String username;
    private String email;

    public AuthResponse(String token, String id, String username, String email) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
    }
}